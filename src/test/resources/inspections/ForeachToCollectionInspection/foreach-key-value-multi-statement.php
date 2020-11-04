<?php

$array = [
    'one' => 'monkey',
    'two' => 'cow',
    'tree' => 'snake',
];

<caret>foreach ($array as $key => $value) {
    if (is_numeric($key)) {
        [$key, $value] = [$value, null];
    }
    doSomething($key);
}
