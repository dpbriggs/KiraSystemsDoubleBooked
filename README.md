# Kira Systems Double Booked Programming Test

This repo is my solution for the Double Booked programming test.

## Overview

This script performs the following:

1. Generate 20 calendar events.
2. Generate a unique sequence of all pairs of events.
3. Filter the pairs of events for overlaps.
4. Pretty print the result.

All of the functionality can be found under:

```
$PROJECT_ROOT/src/coding_test/core.clj
```

## Installation

Clone this project:

```
git clone git@github.com:dpbriggs/KiraSystemsDoubleBooked.git
```

Install dependencies:

```
lein install
```

And run:

```
lein run
```

## Examples


```
➜  coding_test git:(master) ✗ lein run
The following overlaps were found:
(({:start "2018-01-08-10", :end "2018-01-08-13"}
  {:start "2018-01-08-2", :end "2018-01-08-13"})
 ({:start "2018-01-01-9", :end "2018-01-01-11"}
  {:start "2018-01-01-1", :end "2018-01-01-10"})
 ({:start "2018-01-05-11", :end "2018-01-05-15"}
  {:start "2018-01-05-8", :end "2018-01-05-12"})
 ({:start "2018-01-10-5", :end "2018-01-10-12"}
  {:start "2018-01-10-2", :end "2018-01-10-12"})
 ({:start "2018-01-10-9", :end "2018-01-10-10"}
  {:start "2018-01-10-5", :end "2018-01-10-12"})
 ({:start "2018-01-10-9", :end "2018-01-10-10"}
  {:start "2018-01-10-2", :end "2018-01-10-12"})
 ({:start "2018-01-06-5", :end "2018-01-06-12"}
  {:start "2018-01-06-1", :end "2018-01-06-7"}))
```
