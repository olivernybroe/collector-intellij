<?php

$b = <caret>array_map(function($n) {
    return ($n * $n * $n);
}, [1, 2, 3, 4, 5]);
