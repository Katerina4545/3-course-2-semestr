:- dynamic db_yes/1, db_no/1.

rule(1, "visual basic", [1,6,9]).
rule(2, "c++", [1,7,5,10]).
rule(3, "java", [1,10,11,12]).
rule(4, "delphi", [1,12,6,10]).
rule(5, "си", [4,6,10]).
rule(6, "pascal", [4,12,6]).
rule(7, "prolog", [3,8,6]).
rule(8,"flow9", [2,10,12]).
rule(9, "lisp", [2,6,13]).
rule(10, "oz", [3,14,15]).

property(1, "объектно-ориентированный").
property(2, "функциональный").
property(3, "логический").
property(4, "процедурный").
property(5, "поддерживает наследование").
property(6, "простой в освоении").
property(7, "поддерживает полиморфизм").
property(8, "испопользует факты и правила").
property(9, "имеет графический интерфейс").
property(10, "кросплатформенный").
property(11, "требует наличие JVM на устройстве").
property(12, "строго типизированный").
property(13, "с динамической типизация").
property(14, "не использует факты").
property(15, "поддержка мультипарадигменного программирования").

language(X):- rule(_, X, Property), check_property(Property).
language(_):- writeln("Такого языка программирования я не знаю."), fail.

check_property([N | Property]):- property(N, A),yes(A),check_property(Property).
check_property([ ]).

yes(X) :- db_yes(X), !.
yes(X) :- not(no(X)), !,check_if(X).
no(X) :- db_no(X), !.

check_if(X) :- write("Искомый язык должен обладать свойством: "), writeln(X),writeln("? (напишите y/n)"),
read(Reply),remember(Reply, X).

remember(y, X) :- asserta(db_yes(X)).
remember(n, X) :- asserta(db_no(X)),fail.


/*                */
print_list([ ]).
print_list([ Head | Tail ]) :- property(Head,Z),write(Z), nl,print_list(Tail).

print_languages():- forall(rule(_,Name,_),writeln(Name)).
get_description(y):- print_languages(),nl, writeln("Напишите название языка программирования из предложенного списка: "),read(X), rule(_, X, _),describe(X).
get_description(n):- nl, writeln("Всего доброго!"),true.
get_description(_):- writeln("Не знаю такого языка программирования").

describe(X) :- rule(_, X, Properties), write("Свойства языка программирования "), write(X),writeln(":"), print_list(Properties).

/*определение языка*/
find_lang:-retractall(db_yes(_)), retractall(db_no(_)), language(X), writeln("Вы ответили,что Вам важны следующие свойства в языке программирования: "),describe(X),nl, write("По выявленным требованиям Вам подходит - "),nl,writeln(X).

/*выдать сведения о языке*/
give_inf_about:- nl, writeln("Хотите узнать описание какого-либо языка программирования? Напишите(y/n)"), read(Answer), get_description(Answer).

















