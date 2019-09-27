from Trabajo_Practico_4 import *


def carga_auto(fd, n):

    m = open(fd, "wb")
    series = "Mr robot", "Lucifer", "Games of Thrones", "The walking dead", "The preacher", "Limitless", "Breaking bad",\
             "Prision Break", "Scream Queens", "Fear the walking dead", "Vikings", "Band of Brothers", "The pacific", \
             "House of Cards", "Orange is the new black", "The office", "Modern family", "Silicon Valley", "Dr House", \
             "Mad Men", "Narcos", "The last man on Earth", "True detective", "Better call Saul", "Bates Motel", \
             "Strange Things", "Wayward Pines", "White Collar", "Arrow", "The Big Bang Theory"
    series_acum = []
    for i in range(n):
        titulo = random.choice(series)

        while titulo in series_acum:
            titulo = random.choice(series)

        series_acum.append(titulo)
        genero = int(random.randint(0, 5))
        idioma = int(random.randint(0, 4))
        temporadas = int(random.randint(1, 10))
        capitulos = int(random.randint(1, 20))
        duracion = int(random.randint(30, 120))
        ult_temporada = int(random.randint(0, temporadas))
        ult_capitulo = int(random.randint(0, capitulos))
        ser = Serie(titulo, genero, idioma, temporadas, capitulos, duracion, ult_temporada, ult_capitulo)
        pickle.dump(ser, m)

    m.close()

    return n
