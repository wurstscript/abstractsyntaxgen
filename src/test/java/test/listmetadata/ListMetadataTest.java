package test.listmetadata;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ListMetadataTest {
    @Test void copiedListMetadataRetainsAliasesWithoutAnySchemaAdapterOrReferenceRepair() {
        var original=LM.Entries();original.setValues(new ArrayList<>(List.of("original")));original.setAlias(original.getValues());
        for(var copy:List.of(original.copy(),original.copyWithRefs())) {
            assertSame(copy.getValues(),copy.getAlias());assertNotSame(original.getValues(),copy.getAlias());
            copy.getAlias().add("copy");assertEquals(List.of("original"),original.getValues());
        }
    }
}
