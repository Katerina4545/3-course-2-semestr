:- dynamic db_yes/1, db_no/1.

rule(1, "visual basic", [1,6,9]).
rule(2, "c++", [1,7,5,10]).
rule(3, "java", [1,10,11,12]).
rule(4, "delphi", [1,12,6,10]).
rule(5, "��", [4,6,10]).
rule(6, "pascal", [4,12,6]).
rule(7, "prolog", [3,8,6]).
rule(8,"flow9", [2,10,12]).
rule(9, "lisp", [2,6,13]).
rule(10, "oz", [3,14,15]).

property(1, "��������-���������������").
property(2, "��������������").
property(3, "����������").
property(4, "�����������").
property(5, "������������ ������������").
property(6, "������� � ��������").
property(7, "������������ �����������").
property(8, "������������ ����� � �������").
property(9, "����� ����������� ���������").
property(10, "�����������������").
property(11, "������� ������� JVM �� ����������").
property(12, "������ ��������������").
property(13, "� ������������ ���������").
property(14, "�� ���������� �����").
property(15, "��������� �������������������� ����������������").

language(X):- rule(_, X, Property), check_property(Property).
language(_):- writeln("������ ����� ���������������� � �� ����."), fail.

check_property([N | Property]):- property(N, A),yes(A),check_property(Property).
check_property([ ]).

yes(X) :- db_yes(X), !.
yes(X) :- not(no(X)), !,check_if(X).
no(X) :- db_no(X), !.

check_if(X) :- write("������� ���� ������ �������� ���������: "), writeln(X),writeln("? (�������� y/n)"),
read(Reply),remember(Reply, X).

remember(y, X) :- asserta(db_yes(X)).
remember(n, X) :- asserta(db_no(X)),fail.


/*                */
print_list([ ]).
print_list([ Head | Tail ]) :- property(Head,Z),write(Z), nl,print_list(Tail).

print_languages():- forall(rule(_,Name,_),writeln(Name)).
get_description(y):- print_languages(),nl, writeln("�������� �������� ����� ���������������� �� ������������� ������: "),read(X), rule(_, X, _),describe(X).
get_description(n):- nl, writeln("����� �������!"),true.
get_description(_):- writeln("�� ���� ������ ����� ����������������").

describe(X) :- rule(_, X, Properties), write("�������� ����� ���������������� "), write(X),writeln(":"), print_list(Properties).

/*����������� �����*/
find_lang:-retractall(db_yes(_)), retractall(db_no(_)), language(X), writeln("�� ��������,��� ��� ����� ��������� �������� � ����� ����������������: "),describe(X),nl, write("�� ���������� ����������� ��� �������� - "),nl,writeln(X).

/*������ �������� � �����*/
give_inf_about:- nl, writeln("������ ������ �������� ������-���� ����� ����������������? ��������(y/n)"), read(Answer), get_description(Answer).

















