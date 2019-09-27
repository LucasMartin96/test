lista = 9 * [None]
lista[1] = 0
print(lista)
while None in lista:
        lista.remove(None)
print(lista)
