<?php

$array = collect([
    'one',
    'two',
    'tree'
]);

$array->each(fn($item) => doAction($item));
