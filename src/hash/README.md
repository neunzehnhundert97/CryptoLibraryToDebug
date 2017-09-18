# Hash Functions

Please note that due to Java's limitations none of the implemented algorithms will work for any input over about 2^64-1 bytes.
It may be possible to overcome this limitation, but we do not assume it necessary.
Below you find links to the official documents we used as reference and a few thoughts from us.

If you look for it, you can also find the official collection of [test vectors](http://csrc.nist.gov/groups/ST/toolkit/examples.html).

***

* [MD2] (https://www.ietf.org/rfc/rfc1319.txt "RFC 1319")

MD2 was very easy to implement, I got it to work for inputs up to 15 byte in just a few hours. The only problem an error in the checksum when the input length reached 16 bytes. As I compared the algorithms description with its reference implementation I noticed a difference between them. The description says for the checksums loop

	For j = 0 to 15 do
		Set c to M[i*16+j].
		Set C[j] to S[c xor L].
		Set L to C[j].
	end /* of loop on j */
          
while the implementation says

	for (i = 0; i < 16; i++)
		t = checksum[i] ^= PI_SUBST[block[i] ^ t];
		
So there is basically a mistake in the documentation you should take into account.

***

* [MD5] (https://www.ietf.org/rfc/rfc1321.txt "RFC 1321")

MD5 is a little bit harder than MD2 just because you can't but everything in a simple loop. Be cautious when you copy and paste and you won't have much trouble.

***

* [SHA-1](https://tools.ietf.org/html/rfc3174 "RFC 3174")

SHA-1 is easy to write down, easier than MD5 in my opinion. You should be able to optimize it by unfolding the working loop and use a once loaded value multiple times.

***

* [SHA-2](https://tools.ietf.org/html/rfc4634 "RFC 4634")

SHA-2 is not really harder than SHA-1. If you want to program all four variations, there will of course be a little bit more work to be done. As in SHA-1 you can optimize most easily the working loop.

***

* [SHA-3](http://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf "FIPS PUB 202")

Of all hash functions feature until now, SHA-3 or Keccak had the hardest to read publication.