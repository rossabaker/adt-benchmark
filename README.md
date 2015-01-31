# Question

Which is faster in a two-class ADT: a monomorphic method pattern matching
`this`, or polymorphic dispatch?

# Run it

sbt -Dsbt.log.noformat=true run > results.txt

# Summary

* Monomorphic: 13.099489
* Polymorphic: 13.485494

# Disclaimers

1. Microbenchmarking is hard.
2. I'm not very good at it.
