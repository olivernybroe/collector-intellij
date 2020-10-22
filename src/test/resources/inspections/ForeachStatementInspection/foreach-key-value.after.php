<?php

$array = [
    'one' => 'monkey',
    'two' => 'cow',
    'tree' => 'snake',
];

collect($array)->each(function ($animal, $num) {
    echo "{$num} = {$animal}";
});
