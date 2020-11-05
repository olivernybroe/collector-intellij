<?php

<weak_warning descr="'map()->flatten()' used instead of 'flatmap()'">collect($shopifyOrder->fulfillments)
    ->map(
        fn (ShopifyFulfillment $fulfillment) => $this->mapLineItems($order, $shopifyOrder, $fulfillment)
    )
    ->flatten(1)</weak_warning>;
