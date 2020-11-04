<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

collect($array)->each(<weak_warning descr="Closure can be converted to arrow function">function ($item) use ($name) {
    doAction($item, $name);
}</weak_warning>);
