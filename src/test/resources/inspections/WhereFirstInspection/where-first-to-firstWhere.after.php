<?php

collect([
    ['name' => 'Monkey'],
    ['name' => 'Cat'],
])->firstWhere('name', 'Monkey');
