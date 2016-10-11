# AndroidExplorer
This project contains some simple examples of using Android features

## Order of observeOn and subscribeOn

There are four cases:

1. No observeOn and subscribeOn calls.
2. Only observeOn calls.
3. Only subscribeOn calls.
4. Greater than or equal to 1 subscribeOn calls and greater than or equal to 1 observeOn calls.

Let's look at each of those.

1. If there are no observeOn or subscribeOn than all methods will be executed on the current thread (proof test17).
2. If there are only observeOn calls then:
  * All methods before the first (from the top) observeOn will be executed on the current thread.
  * All methods between the first and the second observeOn will be executed on the first scheduler.
  * All methods between the second and the third observeOn will be executed on the second scheduler.
  * and so on...
  Proof test18.
3. If there are only subscribeOn calls then:
  * All methods will be executed on the first from the top scheduler.
  Proof test19.
4. If there are both subscribeOn and observeOn calls then:
  * All methods before the first (from the top) observeOn will be executed on the first (from the top) subscribeOn's scheduler.
  * **All** methods between the first and the second observeOn will be executed on the first(from the top) observeOn's scheduler.
  * **All** methods between the second and the third observeOn will be executed on the second(from the top) observeOn's scheduler.
  * and so on...
  Proof test20 and many others.
