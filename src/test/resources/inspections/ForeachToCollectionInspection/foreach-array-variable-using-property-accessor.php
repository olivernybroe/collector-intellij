<?php

$array =  (object) [
    'one' => [1,2,3],
    'two' => [3,2,1],
    'tree' => [2,1,3],
];

<caret>foreach ($array->one as $item) {
    echo $item;
}
