# Expression Conversion Using Stack

This project demonstrates how to convert between **Infix, Prefix, and Postfix** expressions using **stack-based algorithms**.

---

## 📖 Notations Recap
- **Infix**: Operator is **between operands** → `A + B`
- **Prefix (Polish)**: Operator is **before operands** → `+ A B`
- **Postfix (Reverse Polish)**: Operator is **after operands** → `A B +`

---

## 🔑 Core Idea
- **Operands** → Push to stack  
- **Operators** → Pop two operands from stack, combine, and push result back  
- **Scan direction** depends on notation:
  - **Infix → Postfix/Prefix**: Requires operator precedence handling
  - **Postfix**: Scan **left → right**
  - **Prefix**: Scan **right → left**

---

## 🔄 Conversion Rules

| Conversion         | Scan Direction | Rule |
|--------------------|----------------|------|
| **Infix → Postfix** | Left → Right   | Operands → output, Operators → stack (pop higher/equal precedence), `(` push, `)` pop until `(` |
| **Infix → Prefix**  | Right → Left   | Reverse infix, swap brackets, apply Infix → Postfix, reverse result |
| **Postfix → Infix** | Left → Right   | Operand → push, Operator → pop 2 → `(left op right)` → push |
| **Prefix → Infix**  | Right → Left   | Operand → push, Operator → pop 2 → `(left op right)` → push |
| **Postfix → Prefix**| Left → Right   | Operand → push, Operator → pop 2 → `op + left + right` → push |
| **Prefix → Postfix**| Right → Left   | Operand → push, Operator → pop 2 → `left + right + op` → push |

---
