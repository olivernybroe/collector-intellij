<?php

namespace Illuminate\Support {
    /**
     * Class Collection
     *
     * @package Illuminate\Support
     * @property-read HigherOrderCollectionProxy $map
     */
    class Collection {
        /**
         * Create a new collection.
         *
         * @param  mixed  $items
         */
        public function __construct($items = [])
        {
        }

        /**
         * @return Collection
         */
        public function map($arg) {

        }

        /**
         * @return Collection
         */
        public function flatten($arg) {

        }

        public function each($arg) {

        }

        /**
         * @return Collection
         */
        public function flatMap($arg) {

        }

        /**
         * @return Collection
         */
        public function filter($arg) {

        }

        /**
         * @return Collection
         */
        public function pluck($arg) {

        }

        /**
         * @return int
         */
        public function sum() {

        }

        public function get($key, $default = null)
        {
        }

        /**
         * Get the first item by the given key value pair.
         *
         * @param  string  $key
         * @param  mixed  $operator
         * @param  mixed  $value
         * @return mixed
         */
        public function firstWhere($key, $operator, $value = null)
        {
        }

        /**
         * Get the first item from the collection.
         *
         * @param  callable|null  $callback
         * @param  mixed  $default
         * @return mixed
         */
        public function first(callable $callback = null, $default = null)
        {
        }

        /**
         * Filter items by the given key value pair.
         *
         * @param  string  $key
         * @param  mixed  $operator
         * @param  mixed  $value
         * @return static
         */
        public function where($key, $operator, $value = null)
        {
        }
    }

    /**
     * @mixin \Illuminate\Support\Enumerable
     */
    class HigherOrderCollectionProxy
    {
    }
}

namespace Illuminate\Database\Eloquent {
    class Collection extends \Illuminate\Support\Collection {

    }
}


/**
 * @param $array
 * @return \Illuminate\Support\Collection
 */
function collect($array) {
    return new \Illuminate\Support\Collection();
}