# PCTO Kotlin

## Useful Information

### Type Coercion

The compiler automatically sets all components (it's called type coercion) and the result type to the widest type in the expression.
![type coercion](https://ucarecdn.com/f6fd5591-06db-4ba1-a67f-9c6bfcffd526/)

### Range Wrapping

The **Byte** data type can't handle numbers beyond this range -128..127, to work around this the compiler will **wrap around** when encountering a number larger than that range and continue counting, so (128 becomes -128) and (129 becomes -127), and so on.
This is how the compiler counts: 126 127 -128 -127 -126 -125 -124 -123 -122 -121 -120 -119 .... 0 1 2 3... 127 -128 ...

In our case, we can do the distance from 0 to 127 but from 128 to 200 we wrap around and keep counting 
200 - 128 = 72, so to find our result we start counting from -128 and add 72



## Mind Blowing Code Examples

### Read-only Parameters

```kt
class Contact(val id: Int, var email: String) 
```
Where the `val` **keyword** is used to declare read-only variables.

### When Statement with Any Parameter

```kt
fun main() {
    println(whenAssign("Hello"))
    println(whenAssign(3.4))
    println(whenAssign(1))
    println(whenAssign(MyClass()))
}

fun whenAssign(obj: Any): Any {
    val result = when (obj) {
        1 -> "one"
        "Hello" -> 1
        is Long -> false
        else -> 42
    }
    return result
}
```

### Various Forms of Loops

```kt
for(i in 0..3)

for(i in 0 until 3)

for(i in 2..8 step 2)

for (i in 3 downTo 0)
```
Loops use **ranges** to know how many times an instruction should be executed.

```kt
val x = 2
if (x in 1..5) {
    println("x is in range from 1 to 5")
}

if (x !in 6..10) {
    println("x is not in range from 6 to 10")
}
```

### Equality

Kotlin uses `==` for structural comparison and `===` for referential comparison.

### Conditional Expressions

There is no ternary operator `condition ? then : else` in Kotlin. Instead, `if` may be used as an expression :

```kt
fun max(a: Int, b: Int) = if (a > b) a else b  
```

## Collections

### "It" Abbreviation

```kt
val numbers = listOf(1, -2, 3, -4, 5, -6)

val positives = numbers.filter { x -> x > 0 }

val negatives = numbers.filter { it < 0 }
```
Where `it` is an abbreviation for the **lambda expression**.

### Partition

```kt
al numbers = listOf(1, -2, 3, -4, 5, -6)

val evenOdd = numbers.partition { it % 2 == 0 }
val (positives, negatives) = numbers.partition { it > 0 }
```
Where partition splits the `true` cases in the `positives` variable and the `false` cases into the `negatives` variable.
