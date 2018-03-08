# Design decisions

## 'Classic' visitor instead of dynamic
"A major drawback [of a dynamic visitor] is the loss of compile-time type checking and early detection of errors if the class hierarchy is modified. By using `dynamic`, we also lose refactoring support, “find usages”, and IntelliSense."[1]

1. [The Visitor Pattern and dynamic in C# 4](http://faithlife.codes/blog/2010/03/the_visitor_pattern_and_dynamic_in_c_4/)