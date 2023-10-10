# Exercise Project for Design Patterns Training

Modules represent:

* p01 - Basic scanner
* p02 - **Static Factory Method** in interface `Symbol` 
* p03 - **Flyweight** pattern for caching Symbol representations (without extrinsic state)
* p04 - Minor change using **Command-Query-Separation principle** (no design pattern)
* p05 - Minor change introducing an interface for the Scanner implementation
* p06 - Start of **Interpreter** pattern by parsing all symbols into a final `double` result, however there is no representation of the grammar yet
* p07 - Intermediate step, we are adding the concept of expressions
* p08 - **Bridge**, the previously added expressions define a class for many combinations of (unary/binary) with operators
* p09 - **Composite** pattern by providing a traversable tree of expressions
* p10 - **Internal Iterator** to call a `Consumer` for each expression in the tree
* p11 - Completion of **Interpreter**