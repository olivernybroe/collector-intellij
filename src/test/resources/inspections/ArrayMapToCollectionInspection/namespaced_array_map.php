<?php

namespace MyAwesomeName\Good;

$a = [1, 2, 3, 4, 5];
$b = <caret>\array_map(function($n) {
    return ($n * $n * $n);
}, $a);
