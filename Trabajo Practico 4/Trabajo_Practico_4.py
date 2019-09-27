import pickle
import random
from Carga_Automatica import *

 #queondera
class Serie:

    def __init__(self, titulo, genero, idioma, temporadas, capitulos, duracion, ult_temporada, ult_capitulo):
        self.titu = titulo
        self.gen = genero
        self.idio = idioma
        self.temp = temporadas
        self.cap = capitulos
        self.dura = duracion
        self.ult_temp = ult_temporada
        self.ult_cap = ult_capitulo


def carga(fd, n):

    m = open(fd, "wb")
    for i in range(n):
        print("******************************************************************************************************"
              "*********************************************")
        titulo = input("Ingrese el titulo de la serie: ")
        genero = int(validar_lim(0, 5, "Ingrese el genero de la serie(0-Infantil, 1-Comedia, 2-Romantico, "
                                       "3-Drama, 4-Ciencia Ficcion, 5-Otros) "))
        idioma = int(validar_lim(0, 4, "Ingrese el idioma de la serie(0-Español, 1-Ingles, 2-Frances, 3-Portugues, "
                                       "4-Otros) "))
        temporadas = int(validar(0, "Ingrese cantidad de temporadas de la serie "))
        capitulos = int(validar(0, "Ingrese cantidad de capitulos por temporada de la serie "))
        duracion = int(validar(0, "Ingrese duracion de cada capitulo de la serie "))
        ult_temporada = validar_lim(1, temporadas, "Ingrese ultima temporada vista('0'si no vio ninguna) ")
        ult_capitulo = validar_lim(1, capitulos, "Ingrese el ultimo capitulo visto('0'si no vio ninguna) ")
        ser = Serie(titulo, genero, idioma, temporadas, capitulos, duracion, ult_temporada, ult_capitulo)
        pickle.dump(ser, m)

    m.close()

# Validaciones


def validar_lim(a, b, info):

        num = int(input(str(info)+"(entre " + str(a) + " y " + str(b) + "): "))

        while num < a or num > b:
            num = int(input("Error... era entre " + str(a) + " y " + str(b) + ". De nuevo: "))

        return num


def validar(a, info):

    num = int(input(str(info)+"(mayor a "+str(a)+"): "))

    while num <= a:
        print("ERROR!")

        num = int(input("Se pidio mayor a" + str(a) + "Cargue nuevamente: "))

    return num


# Funcion to_string


def to_string(s):

    r = " "
    r += "{:<40}".format("Titulo: " + s.titu)
    r += "{:<15}".format("Genero: " + str(s.gen))
    r += "{:<10}".format("Idioma: " + str(s.idio))

    return r


# Opcion 2


def opcion2(v, fd):

    m = open(fd, "rb")

    for i in range(len(v)):
        v[i] = pickle.load(m)

    m.close()
    mostrar(v)
    ordenar(v)


def mostrar(v):

    for i in range(len(v)):

        print(to_string(v[i]))


def ordenar(v):

    n = len(v)

    for i in range(n-1):
        for j in range(i+1, n):
            if v[i].titu > v[j].titu:
                v[i], v[j] = v[j], v[i]


# Opcion 3


def opcion3(v):

    bandera = False
    cont_series = 0
    acum_tiempo = 0
    n = int(validar(0, "Ingrese la cantidad de series que quiere en su lista "))
    g = int(validar_lim(0, 5, "Ingrese el genero de las series que desea en su lista(0-Infantil, 1-Comedia, "
                              "2-Romantico, 3-Drama, 4-Ciencia Ficcion, 5-Otros)"))
    lista = n * [None]

    for i in range(len(v)):

        if cont_series < n:
            if v[i].gen == g:
                lista[cont_series] = v[i]
                acum_tiempo += v[i].dura * v[i].cap * v[i].temp
                cont_series += 1

        else:
            bandera = True
            break

    if not bandera:

        if cont_series > 0:

            while None in lista:
                lista.remove(None)

            print("Solo se encontraron ", cont_series, " series con el genero especificado...")
            mostrar(lista)
            print("Duracion total de la lista: ", acum_tiempo, "min")

        else:
            print("No se encontraron series con el genero especificado ")

    else:
        print("Se completo la lista perfectamente...")
        mostrar(lista)
        print("Duracion total de la lista: ", acum_tiempo)


# Opcion 4

def opcion4(v):

    matriz = crear_matriz(5, 4)

    for i in range(len(v)):
        matriz[v[i].gen][v[i].gen] += 1

    mostrar_matriz(matriz)


def crear_matriz(f, c):

    matriz = [0] * f

    for i in range(len(matriz)):
        matriz[i] = [0] * c
    return matriz


def mostrar_matriz(matriz):
    v_genero = 6 * [None]
    v_idioma = 5 * [None]
    v_genero[0] = "Infantil"
    v_genero[1] = "Comedia"
    v_genero[2] = "Romantico"
    v_genero[3] = "Drama"
    v_genero[4] = "Ciencia Ficcion"
    v_genero[5] = "Otros"

    v_idioma[0] = "Español"
    v_idioma[1] = "Ingles"
    v_idioma[2] = "Frances"
    v_idioma[3] = "Portugues"
    v_idioma[4] = "Otros"

    for i in range(len(matriz)):
        for j in range(len(matriz[i])):
            if matriz[i][j] != 0:

                print("La cantidad de series de genero ", v_genero[i], " e idioma ", v_genero[j], " es: ", matriz[i][j])

# Opcion 5


def busqueda(v):

    busq = input("Ingrese el titulo de la serie que desea buscar: ")
    busq = busq.upper()

    for i in range(len(v)):
        serie = v[i].titu
        serie = serie.upper()

        if busq == serie:
            return v[i]

    return -1


def opcion5(v):

    serie = busqueda(v)

    if serie == -1:
        print("No se encontro una serie con ese titulo..")

    elif serie.temp == serie.ult_temp and serie.cap == serie.ult_cap:
        print("La serie ", serie.titu, " fue terminada.")

    else:
        print("La serie ", serie.titu, " no fue terminada.")


# Opcion 6


def opcion6(v):

    acum_tiempo = 0
    v_sinver = []

    for i in range(len(v)):

        if v[i].ult_temp == 0:
            v_sinver.append(v[i])
            acum_tiempo += v[i].dura * v[i].cap * v[i].temp

    ordenar(v_sinver)
    mostrar(v_sinver)

    print("Duracion total de la lista: ", acum_tiempo)


# Opcion 7


def opcion7(v):

    cont = 0
    idioma = validar_lim(0, 4, "Ingrese el idioma que quiere generar la lista(0-Español, 1-Ingles, 2-Frances, "
                               "3-Portugues 4-Otros) ")
    fd = "SeriesIdioma" + str(idioma) + ".dat"
    m = open(fd, "wb")

    for i in range(len(v)):

        if v[i].idio == idioma and v[i].temp > 1:
            pickle.dump(v[i], m)
            cont += 1

    m.close()
    mostrar_archivo(cont, fd)


def mostrar_archivo(cont, fd):

    m = open(fd, "rb")

    for i in range(cont):
        serie = pickle.load(m)
        print(to_string(serie))

    m.close()


def test():

    bandera = False
    fd = "Series.dat"
    opc = 0

    while opc != 8:

        print("\n******************************************************************************************************"
              "*********************************************")

        opc = validar_lim(1, 8, "\nMenu de opciones:\n\n\t1. Cargar series\n\t2. Generar vector y mostrar\n\t"
                                "3. Generar lista por genero y mostrar su duracion\n\t4. Mostrar cantidad de series"
                                "por genero y por idioma\n\t5. Buscar serie por titulo"
                                "\n\t6. Generar vector con series aun no vistas\n\t7. Generar archivo por idioma\n\t"
                                "\n\t8. Salir.\n\nOpcion:")

        print("\n******************************************************************************************************"
              "*********************************************")
        print("")

        if opc == 1 or not bandera and opc != 8:

            if not bandera and opc != 1 :
                print("Antes de ejecutar la opcion ", opc, " debera cargar las series...")

            op_carga = validar_lim(1, 2, "Tipo de carga:\n\t1- Automatica\n\t2- Manual\n\tOpcion ")
            if op_carga == 1:
                n = validar_lim(0, 30, "Ingrese la cantidad de series que desea")
                carga_auto(fd, n)
                v = n * [None]

            else:
                n = validar(0, "Ingrese la cantidad de series ")
                carga(fd, n)
                v = n * [None]

            bandera = True

        elif opc == 2:
            opcion2(v, fd)

        elif opc == 3:
            opcion3(v)

        elif opc == 4:
            opcion4(v)

        elif opc == 5:
            opcion5(v)

        elif opc == 6:
            opcion6(v)

        elif opc == 7:
            opcion7(v)

        elif opc == 8:

            print("Gracias por utilizar nuestro servicio vuelva pronto!")
            print("\n\n---------------------------- Programa finalizado ---------------------------")

if __name__ == '__main__':
    test()
