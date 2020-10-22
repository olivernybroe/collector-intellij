<?php

$array = [
    'one' => 'monkey',
    'two' => 'cow',
    'tree' => 'snake',
];

<caret>foreach ($array as $num => $animal) {
    echo "{$num} = {$animal}";
}
