<?php

collect([
    ['name' => 'Monkey'],
    ['name' => 'Cat'],
])->contains('name', 'Monkey');
