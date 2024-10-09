<?php

$array = collect([
    'one',
    'two',
    'tree'
]);

$array->each(<caret>function ($item) {
    doAction($item);
});
