package test.ownedmetadata;

import java.util.List;

/** Type semantics only: generated code enumerates all fields and containers. */
public class SchemaMetadataAdapter implements OMNodeGraphs.Adapter {
    public boolean externalReference(Object value) { return value instanceof GraphContractsTest.ForeignNode; }
    public OMNodeGraphs.MapKind mapKind(java.util.Map<?,?> value) {
        return value instanceof GraphContractsTest.InsertionMap ? OMNodeGraphs.MapKind.EQUALITY : OMNodeGraphs.Adapter.super.mapKind(value);
    }
    public OMNodeGraphs.SetKind setKind(java.util.Set<?> value) {
        return value instanceof GraphContractsTest.InsertionSet ? OMNodeGraphs.SetKind.EQUALITY : OMNodeGraphs.Adapter.super.setKind(value);
    }
    public OMNodeGraphs.Parts decompose(Object value) {
        return value instanceof GraphContractsTest.Choice choice ? new OMNodeGraphs.Parts(choice.left() ? "left" : "right",List.of(choice.value())) : null;
    }
    public Object compose(String tag,List<Object> values) { return new GraphContractsTest.Choice(tag.equals("left"),values.get(0)); }
}
