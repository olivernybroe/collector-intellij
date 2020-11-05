<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

<caret>foreach ($array as $item) {
    if ($item === $name) {
        echo "found " . $name;
    }
}
