# PCTO

## Mind Blowing Code Examples

### Read-only Parameters

```k
class Contact(val id: Int, var email: String) 
```
Where the `val` keyword is used to declare read-only variables.

### Class Inheritance with Parameterized Constructor

```kt
open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String) : Lion(name = name, origin = "India") // 1

fun main() {
    val lion: Lion = Asiatic("Rufo")                              // 2
    lion.sayHello()
}
```
