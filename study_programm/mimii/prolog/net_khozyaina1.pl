
cat(chelsi).
cat(bagira).
cat(sonya).
color(chelsi, brown).
color(bagira, black).
color(sonya, orange).
dog(reks).
dog(naida).
dog(sharik).
color(reks, black).
color(sharik, white).
animal(X):- cat(X).
animal(X):- dog(X).
rodoslovnaya(X):- animal(X), owner(ivan, X).
rodoslovnaya(X):- animal(X), owner(anna, X).
owner(ivan, X):- color(X, black);color(X, brown).
owner(anna,X):- cat(X),not(color(X, white)), not(owner(ivan,X)).
owner(petr, naida):- not(owner(anna, reks)),not(rodoslovnaya(sharic)).
no_owner(Anim_name, Owner_name):- animal(Anim_name),not(owner(Owner_name, Anim_name)).
/*Anim_name = sharik.*/





