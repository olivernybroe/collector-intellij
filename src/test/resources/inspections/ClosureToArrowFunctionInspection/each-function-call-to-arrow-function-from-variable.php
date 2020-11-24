<?php

$array = collect([
    'one',
    'two',
    'tree'
]);

$array->each(<weak_warning descr="Closure can be converted to arrow function">function ($item) {
    doAction($item);
}</weak_warning>);
