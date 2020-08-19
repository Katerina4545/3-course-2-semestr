/*
- animal(Anim_name),not(owner(Owner_name, Anim_name)).
Anim_name = spot.
*/
cat(butsi).
cat(korni).
cat(mak).
color(butsi, brown).
color(korni, black).
color(mak, red).
dog(flash).
dog(rover).
dog(spot).
color(rover, red).
color(spot, white).
animal(X)- cat(X).
animal(X)- dog(X).
rodoslovnaya(X)- animal(X), owner(tom, X).
rodoslovnaya(X)- animal(X), owner(kate, X).
owner(tom, X)- color(X, black); color(X, brown).
owner(kate,X)- dog(X), not(color(X, white)), not(owner(tom,X)).
owner(alan, mak)- not(owner(kate, butsi)), not(rodoslovnaya(spot)).
color(flash, spotted).
