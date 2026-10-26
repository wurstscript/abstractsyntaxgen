# Generator contracts for incremental compiler artifacts

Complete these generator-owned features before widening Wurst backend caching. Keep existing unannotated schemas compatible; no generated Java is edited by hand.

1. Owned metadata collections: tracked List/Set/Map operations and nested views, defensive container copies, alias preservation, detached-view lifetime, fail-before-write checks. Cover constructors, persistent fields and copy variants. Explicitly diagnose structural container cycles; AST reference cycles belong to snapshot support.
2. Structured reference metadata: schema-visible reference roles inside containers and supported sum values; generated traversal/repair/codec coverage without a compiler-maintained field list. Unknown external value semantics use a documented adapter contract, not silent guesses.
3. Semantic versus source revisions: schema field roles, ownership-edge propagation, metadata/list mutations, copy/reset behavior, no propagation through reference targets.
4. Immutable snapshots and ownership validation: generated private allocation/population, iterative deep-tree handling, internal cycles and external symbolic references, strict detached values, round trips and mutation scopes that reject foreign writes before changing data.

Exit gates: adversarial regression tests and planted mutants, all generator tests on Java25/27, deterministic output, all real Wurst schemas generate and compile, and independent Wurst fresh-build oracles. Preserve emitted-program semantics; compiler dependency facts and compile-time execution remain compiler-owned. Do not claim a hot-reload latency improvement until a backend consumer proves bounded work on real edits.

Implementation status: all four contracts are implemented. Regression coverage includes schema-role
determinism, deep graph/container round trips, reference-list and nested-reference repair, source-domain
aliasing, failed attachment atomicity and foreign-write guards. Four planted faults (source-domain
notification, nested-reference remapping, foreign-write checks and detached-view lifetime) each fail
their behavioral regression; the restored implementation passes. All four real Wurst schemas generate
and compile, with 96 focused incremental-backend, fresh-build and determinism oracles as the consumer
integration gate. Real-map replay validates independent Lua and manifest equality; acceptance warnings
and backend work counts must still be reported separately from correctness.

Compiler adoption remains compiler-owned: dependency meaning, reachability, compile-time effects,
specialization identities, symbol keys and custom value adapters. These are not missing generator
field traversal or ownership features. Do not claim a latency gain before affected-function work is
measured in the backend consumer.
