# Advanced-Programming-Methods-Lab1
Lab work

Project that can calculate the expression of this form: number1 op number2 op .... op numberN, where numberK is a complex number (a + b*i). 
Usefull patterns:
- Singleton => restricts the instantiation of a class, only one object of this kind "lives" in whole app.
- Factory => depending on a parameter, the Factory object returns to the user the requested object ( depeding of the operator op, it gives me an object that knows to solve that operation)
- Template => defines a contract between user and app,  defining a skeleton of an algorithm in an operation ( in my case: The computation of addition, substraction, multiply, division)
