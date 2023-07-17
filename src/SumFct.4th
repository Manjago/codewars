: fibosum ( sum, second, first -- sum, second, first)
    \ 1, 1, 2, 3, 5, 8 ... - calculate sum of Nth fibonacci sequance members
    \ first - "first" member of fibonacci sequence
    \ second - "second" member of fibonacci sequence
    \ sum - current sum
    OVER    \ sum, second, first -- sum, second, first, second
    +       \ sum, second, first, second -- sum, second, next(first+second)
    ROT     \ sum, second, next(first+second) -- second, next(first+second), sum
    OVER    \ second, next(first+second), sum -- second, next(first+second), sum, next(first+second)
    +       \ second, next(first+second), sum, next(first+second) -- second, next(first+second), updatedSum
            \ rename [second, next(first+second), updatedSum] to [first, second, sum]
    SWAP    \ first, second, sum -- first, sum, second
    ROT     \ first, sum, second -- sum, second, first
;

: perimeter ( n -- result )
    \ https://www.codewars.com/kata/559a28007caad2ac4e000083/train/forth
    1 + \ n -- n+1
    0 \  n+1 -- n+1 sum
    SWAP \ n+1 sum -- sum n+1
    0 \ sum n+1 -- sum n+1 second
    SWAP \ sum n+1 second -- sum second n+1
    1 \ sum second n+1 -- sum second n+1 first
    SWAP \ sum second n+1 first -- sum second first n+1
    0 DO
      fibosum
    LOOP
    DROP \ sum, second, first -- sum, second 
    DROP \ sum, second -- sum
    4 *  \ sum -- sum*4 (answer)
;



\ 0 0 1 fibosum .S
30 perimeter
. CR bye