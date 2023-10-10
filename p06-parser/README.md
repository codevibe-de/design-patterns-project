# Parser

This module focuses on parsing the stream of `Symbol` instances available from the `Scanner`
into a resulting `double` value.

````text
additiveExpr -> multiplicativeExpr [+|-] multiplicativeExpr
multiplicativeExpr -> atomicExpr [*|/] atomicExpr
atomicExpr -> [+|-] ( numberExpr|additiveExpr )
````