# Object-Oriented Design Principles

> **Encapsulation** Identify the aspects of your application that vary and separate them from what stays the same.

By encapsulating the parts that vary, you can alter or extend these without affecting the parts that don't vary.

> Program to an interface not an implementation.

In this way client code will simply need to know the behaviour of the components rather than worry about the details of their implementation.

> Favor composition over inheritance.

> Strive for loosely coupled designs between objects that interact.

Loosely coupled designs allow us to build flexible programs that can easily handle change because the interdependency between objects is minimized.

> **Open-Closed Principle** Classes should be open for extension, but closed for modification.

The goal is to allow classes whose behaviour can easily be extended without having to modify existing code.

> **Dependency Inversion Principle** Depend upon abstractions.  Do not depend upon concrete classes.

High-level components should not depend on low-level components.  Both should depend on abstractions.