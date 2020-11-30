<?php

class HigherOrderMethod {
    public function data() {
        return 'works';
    }
}

collect([
    new HigherOrderMethod()
])->map->data()-><caret>
