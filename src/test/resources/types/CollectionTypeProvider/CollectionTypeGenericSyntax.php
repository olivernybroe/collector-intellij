<?php

class MyClass {
    public function myMethod() {
        return 'works';
    }

    public function mySecondMethod() {
        return 'works second';
    }
}

/** @var \Illuminate\Support\Collection<MyClass> $data */
$data = collect([
    new MyClass(),
    new MyClass()
]);

$data->get(0)-><caret>
