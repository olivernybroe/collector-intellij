<?php

collect($array)->each(function ($item) {
    echo "$item->works";
});
