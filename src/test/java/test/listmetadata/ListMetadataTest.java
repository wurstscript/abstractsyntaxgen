package test.listmetadata;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ListMetadataTest {
    @Test void copiedListMetadataRetainsAliasesWithoutAnySchemaAdapterOrReferenceRepair() {
        var original=LM.PlainEntries();original.setValues(new ArrayList<>(List.of("original")));original.setAlias(original.getValues());
        for(var copy:List.of(original.copy(),original.copyWithRefs())) {
            assertSame(copy.getValues(),copy.getAlias());assertNotSame(original.getValues(),copy.getAlias());
            copy.getAlias().add("copy");assertEquals(List.of("original"),original.getValues());
        }
    }
    @Test void selectiveRepairKeepsTrackedConstructorAliasesInOpaqueFields() {
        for(boolean nested:List.of(false,true)) {
            var original=LM.Node(new ArrayList<>(List.of("original")),new Object());
            original.setMemo(nested ? new ArrayList<>(List.of(original.getData())) : original.getData());
            var copy=original.copyWithRefs();Object alias=nested ? ((List<?>)copy.getMemo()).get(0) : copy.getMemo();
            assertSame(copy.getData(),alias);int before=copy.modificationCount();
            copy.getData().add("copy");assertNotEquals(before,copy.modificationCount());assertEquals(List.of("original"),original.getData());
        }
    }
    @Test void selectiveRepairKeepsTrackedListPersistentAliasesInOpaqueFields() {
        var entries=LM.Entries();entries.setValues(new ArrayList<>(List.of("original")));entries.setAlias(entries.getValues());entries.setObserver(new ArrayList<>(List.of(entries.getValues())));
        var original=LM.Holder(entries);var copy=original.copyWithRefs();
        assertSame(copy.getEntries().getValues(),((List<?>)copy.getEntries().getObserver()).get(0));
        assertSame(copy.getEntries().getValues(),copy.getEntries().getAlias());
        int before=copy.modificationCount();copy.getEntries().getValues().add("copy");assertNotEquals(before,copy.modificationCount());
        assertEquals(List.of("original"),original.getEntries().getValues());
    }
}
