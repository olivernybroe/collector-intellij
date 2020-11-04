<?php

$array = [
    'one' => 'monkey',
    'two' => 'cow',
    'tree' => 'snake',
];

collect($array)->each(function ($value, $key) {
    if (is_numeric($key)) {
        [$key, $value] = [$value, null];
    }
    doSomething($key);
});
