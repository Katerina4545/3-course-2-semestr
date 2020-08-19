:-dynamic db_yes/1, db_no/1.

/*�������*/
rule(1,"���",[1,7,11,13]).
rule(2,"�����",[1,11,13]).
rule(3,"����",[1,7,12,13]).
rule(4,"������",[2,6,11]).
rule(5,"���",[3,9]).
rule(6,"���",[4,14]).
rule(7,"�����",[2,11]).
rule(8,"����",[16]).
rule(9,"������",[15]).
rule(10,"����",[2]).

/*��������*/
property(1,"���������� ������").
property(2,"�������� �������� ������").
property(3,"��������� ������� ������").
property(4,"��������� ��������� ������").
property(6,"����� ����� � ������� ��������").
property(7,"����������� �����").
property(9,"������������ �����").
property(11,"�������� ������ �����").
property(12,"�� ����� ������ �����").
property(13,"������ � ���� �����").
property(14,"������ � ���� �������").
property(15,"������ � ���� ��������").
property(16,"��������� �������� ������").

/*�������*/
tree(X):-rule(_,X,Property), check_property(Property).
tree(_):-write("������ ������ � �� ����� :("),fail.
check_property([N|Property]):-property(N,A),yes(A),check_property(Property).
check_property([]).
yes(X):-db_yes(X),!.
yes(X):-not(no(X)),!,check_if(X).
no(X):-db_no(X),!.
check_if(X):-write("������ ����� "),write(X),writeln("?"),
    read(Reply),remember(Reply,X).
remember(��, X):-asserta(db_yes(X)).
remember(���, X):-asserta(db_no(X)),fail.

/*������ ������ ���� ��������*/
print_obj():- forall(rule(_,Name,_),writeln(Name)).

/*������ ������*/
print_list([ ]).
print_list([ Head | Tail ]) :- property(Head,Z),writeln(Z), nl, print_list(Tail).

/*���������� ���������� �������*/
describe_decision(X) :- rule(_, X, Properties), writeln("������� ������� �� ������ ������ ������: "), print_list(Properties).

/*���������� �������� �������*/
describe_tree(X) :- rule(_, X, Properties), print_list(Properties).

get_description(y):-print_obj(),nl, writeln("�������� �������� ������ �� ������: "),read(X), rule(_, X, _),describe_tree(X).
get_description(n):- nl, writeln("�� ��������."),true.
get_description(_):- writeln("����� ������ ���� y/n.").

/*������ �������� �������*/
tree_description():- writeln("������ ������ �������� ������-�� ������?(y/n)"),read(Answer), get_description(Answer),!.

game:-writeln("��� ����� ������� � ����:"), print_obj(),writeln("�������� �� ��������� �������:"),
    retractall(db_yes(_)),retractall(db_no(_)),
    tree(X),write("���� ������ ���������� - "),
    writeln(X), describe_decision(X).




