# PCTO Kotlin

## Useful Information

### Type Coercion

The compiler automatically sets all components (it's called type coercion) and the result type to the widest type in the expression.
![type coercion](https://ucarecdn.com/f6fd5591-06db-4ba1-a67f-9c6bfcffd526/)

When assigning to a variable an operation between two different sized numbers **type coercion** accours, and the result before the assignment assumes the size of the "biggest" number, for example :
```kt
val b1: Byte = 5
val s1: Short = 2 + b1   // Line 3
val s2: Short = 10 + 3L  // Line 4
```
Where :
+ **Line 3** : 2 is an `Int`and b1 is a `Byte`, type coercion will result in 7 as `Int`
+ **Line 4** : 10 is an `Int` and 3L is a `Long`, type coercion will result in 13 as `Long`

Both the results that will be assigned to a `Short` variable will give a compile error.

### Range Wrapping

The **Byte** data type can't handle numbers beyond this range -128..127, to work around this the compiler will **wrap around** when encountering a number larger than that range and continue counting, so (128 becomes -128) and (129 becomes -127), and so on.
This is how the compiler counts: 126 127 -128 -127 -126 -125 -124 -123 -122 -121 -120 -119 .... 0 1 2 3... 127 -128 ...
In fact :
```kt
val a: Byte = 100
val b: Byte = (a + a).toByte()
```
The value stored in `b` will be -56, because in our case we have to wrap around the range from 128 to 200 and keep counting.
200 - 128 = 72, so to find our result we start counting from -128 and add 72 to make -56.

Another example would be :
```kt
// MAX_VALUE: Int = 2147483647
var d: Int = 2147483647
d += 1
println(d) // -2147483648
```

### "Unsafe" cast operator

Usually, the cast operator throws an exception if the cast isn't possible and so, it's called unsafe.
The unsafe cast in Kotlin is done by the infix operator `as`.
```kt
val x: String = y as String
```
Note that null cannot be cast to String, as this type is not nullable. 
If y is null, the code above throws an exception. 
To make code like this correct for null values, use the nullable type on the right-hand side of the cast:
```kt
val x: String? = y as String?
```

### "Safe" (nullable) cast operator

To avoid exceptions, use the safe cast operator as?, which returns null on failure.
```kt
val x: String? = y as? String
```
Note that despite the fact that the right-hand side of as? is a non-null type String, the result of the cast is nullable.

### Elvis Operator

When you have a nullable reference, `b`, you can say "if `b` is not `null`, use it, otherwise use some non-null value" :
```kt
val l: Int = if (b != null) b.length else -1
```
Instead of writing the complete `if` expression, you can also express this with the Elvis operator `?:` :
```kt
val l = b?.length ?: -1
```
If the expression to the left of `?:` is not `null`, the Elvis operator returns it, otherwise it returns the expression to the right. 
Note that the expression on the right-hand side is evaluated only if the left-hand side is `null`.

Since `throw` and `return` are expressions in Kotlin, they can also be used on the right-hand side of the Elvis operator. 
This can be handy, for example, when checking function arguments:
```kt
fun foo(node: Node): String? {
    val parent = node.getParent() ?: return null
    val name = node.getName() ?: throw IllegalArgumentException("name expected")
    // ...
}
```

### The !! Operator

The third option is for NPE-lovers : the not-null assertion operator (`!!`) converts any value to a non-null type and throws an exception if the value is `null`. 
You can write `b!!`, and this will return a non-null value of `b` (for example, a `String` in our example) or throw an NPE if `b` is `null`:
```kt
val l = b!!.length
```
Thus, if you want an NPE, you can have it, but you have to ask for it explicitly and it won't appear out of the blue.

### Naming Rules

Kotlin has several rules:
+ Names are case-sensitive (number is not the same as Number);
+ Each name can include only letters, digits, and underscores;
+ A name cannot start with a digit;
+ A name cannot be a keyword (for example, val, var, fun are illegal).

So, no whitespaces are allowed in a variable's name. You can use spaces only with backticks:
```kt
val `good name` = 5
val bad name = 2 // will not work
```

### Very Good Lists vs Arrays Explanation

Source : [Stack Overflow](https://stackoverflow.com/a/36263748)








## Code Examples

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

repeat(i)
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
