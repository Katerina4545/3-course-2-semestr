clear;
n = 4;
T0 = 1.32;
Ti = 5.812;
K = 1.38;
Tz = 1.2;

// for Pi-reg
//Td = 0; 

Td = Ti/4;
Tc = Td/8;

h = 1.2/100;
s = poly(0, 's');

//определяем переходную функцию 
W1 = (1 - Tz*s + (Tz*s)^2/2-(Tz*s)^3/6+(Tz*s)^4/(6*4))/(1 + T0*s)^n
W1 = [1 + 1/(Ti*s)+(Td*s/(1+Tc*s))]*K*W1
W=W1/(1 + W1)

//задаем линейную систему через переходную функцию  
sl = syslin('c', W);

//непрерывный случай
[A] = abcd(sl);       //задание матрицы в пространстве состояний
E = eye(A);               //единичная матрица
H = lyap(A, -E, 'c');       //решение уравнения Ляпунова
l = spec(H)               //собственные значения  матрицы H
if l > 0 then             // А устойчива, если все собственные значения Н положительны (ДУ устойчиво)
k = norm(H, 2);          //если А устойчива, то показатель устойчивости = норма Н
else                     //иначе стремиться в бесконечности
k = %inf
end

//дискретный случай
dicrMat = dscr(sl, h); //преобразуем линейную систему в дискретную 
Ad = dicrMat.A;
Ed = -eye(Ad);
Hd = lyap(Ad, Ed, 'd');
ld = spec(Hd)
if ld > 0 then
kd = norm(Hd, 2);
else
kd = %inf
end


