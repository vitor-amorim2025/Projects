def verifica_vitoria(matriz:list, qtd_mina:int) -> bool:    # Verifica se todas as casas vazias foram abertas
    fechados = 0
    for i in range(len(matriz)):
        for j in range(len(matriz[0])):
            if matriz[i][j]["aberto"] == False:
                fechados += 1
    if fechados == qtd_mina:
        return True
    else:
        return False

def revela_bombas(matriz:list):     # Revela todas as bombas após derrota
    for i in range(len(matriz)):
        for j in range(len(matriz[0])):
            if matriz[i][j]["mina"] == True:
                matriz[i][j]["aberto"] = True

def verifica_bombas(matriz:list, l:int, c:int) -> bool:     # Verifica se é uma mina
    if matriz[l][c]["mina"] == False:
        return False
    else:
        return True

def efeito_cascata_dfs(matriz:list, l:int, c:int):      # Abre todas as casas que não tiverem minas adjacentes
    if (matriz[l][c]["aberto"] == False) and (matriz[l][c]["bandeira"] == False):
        if matriz[l][c]["vizinhos"] > 0:
            matriz[l][c]["aberto"] = True
        else:
            matriz[l][c]["aberto"] = True
            for i in range(-1, 2):  # Loops para verificar as 8 posições adjacentes
                for j in range(-1, 2):
                    if (c+j >= 0 and c+j < 10) and (l+i >= 0 and l+i < 10): # Verifica se a coordenada existe na matriz
                        efeito_cascata_dfs(matriz, l+i, c+j)

def verifica_bandeiras(matriz:list)->bool:  # Verifica se há bandeiras alguma bandeira no campo
    for i in range(len(matriz)):
        for j in range(len(matriz[0])):
            if matriz[i][j]["bandeira"] == True:
                return True
    return False
                
def gera_campo(matriz:list):    # Função para mostrar o campo pro jogador
    print(f"   1 2 3 4 5 6 7 8 9 10")
    for i in range(len(matriz)):
        if i==9:
            print(f"{i+1} ", end="")
            for j in range(len(matriz[0])):
                if j<9:
                    if matriz[i][j]["bandeira"] == True:
                        print(f"B", end=" ")
                    elif matriz[i][j]["aberto"] == False:
                        print(f".", end=" ")
                    elif matriz[i][j]["mina"] == True:
                        print(f"*", end=" ")
                    else:
                        print(f"{matriz[i][j]["vizinhos"]}", end=" ")
                else:
                    if matriz[i][j]["bandeira"] == True:
                        print(f"B", end=" ")
                    elif matriz[i][j]["aberto"] == False:
                        print(f".", end=" ")
                    elif matriz[i][j]["mina"] == True:
                        print(f"*", end=" ")
                    else:
                        print(f"{matriz[i][j]["vizinhos"]}", end=" ")
            print()
        else:
            print(f"{i+1}  ", end="")
            for j in range(len(matriz[0])):
                if j<9:
                    if matriz[i][j]["bandeira"] == True:
                        print(f"B", end=" ")
                    elif matriz[i][j]["aberto"] == False:
                        print(f".", end=" ")
                    elif matriz[i][j]["mina"] == True:
                        print(f"*", end=" ")
                    else:
                        print(f"{matriz[i][j]["vizinhos"]}", end=" ")
                else:
                    if matriz[i][j]["bandeira"] == True:
                        print(f"B", end=" ")
                    elif matriz[i][j]["aberto"] == False:
                        print(f".", end=" ")
                    elif matriz[i][j]["mina"] == True:
                        print(f"*", end=" ")
                    else:
                        print(f"{matriz[i][j]["vizinhos"]}", end=" ")
            print()
            
def bandeira_matriz(campo:list, l:int, c:int, colocar=False):   # Põe ou tira uma bandeira
    if colocar:
        for i in range(len(campo)):
            if (i+1) == l:
                for j in range(10):
                    if (j+1) == c:
                        campo[i][j]["bandeira"] = True
                        break
                break
    else:
        for i in range(len(campo)):
            if (i+1) == l:
                for j in range(10):
                    if (j+1) == c:
                        campo[i][j]["bandeira"] = False
                        break
                break

def conta_vizinhos(matriz:list, l:int, c:int)->int:    # Varre as casas adjacentes
    vizi = 0
    for i in range(-1, 2):  # Loops para verificar as 8 posições adjacentes
        for j in range(-1, 2):
            if (c+j >= 0 and c+j < 10) and (l+i >= 0 and l+i < 10): # Verifica se a coordenada existe na matriz
                if matriz[l+i][c+j]["mina"] == True:
                    vizi += 1
    return vizi

def gera_matriz_campo(num_minas:int) -> list:   # Cria a matriz por trás do campo
    import random
    posicoes_minas = []
    while len(posicoes_minas) < num_minas: # Loop pra sortear as minas
        posi = random.randint(0,99)
        if posi not in posicoes_minas:
            posicoes_minas.append(posi)
    campo = []
    for i in range(10): # Loop para criar a matriz inicial
        linha = []
        for j in range(10):
            if (i*10 + j) in posicoes_minas:
                linha.append({"mina": True, "aberto": False, "bandeira": False, "vizinhos": 0})
            else:
                linha.append({"mina": False, "aberto": False, "bandeira": False, "vizinhos": 0})
        campo.append(linha)

    for i in range(10): # Loop para atualizar o número de vizinhos
        for j in range(10):
            campo[i][j]["vizinhos"] = conta_vizinhos(campo, i, j)
    return campo
    
def main():
    import os
    while True: # Loop para rodar as partidas
        print(f"Selecione a quantidade de minas a serem geradas (0 para sair).")
        num_minas = int(input())
        if num_minas == 0:
            os.system("cls")
            break
        else:
            os.system("cls")
            print(f"Insira as coordenadas da posição inicial do jogo.")
            matriz = gera_matriz_campo(num_minas)
            gera_campo(matriz)

            while True: # Loop para rodar as decisões do jogador
                if not verifica_bandeiras(matriz):
                    print(f"1. Abrir quadrado\n2. Colocar bandeira")
                    resp = int(input())
                    if resp == 1:
                        print(f"Insira a coordenada para abertura.")
                        linha = int(input())
                        coluna = int(input())
                        if not verifica_bombas(matriz, linha-1, coluna-1):
                            efeito_cascata_dfs(matriz, linha-1, coluna-1)
                            gera_campo(matriz)
                            if verifica_vitoria(matriz,num_minas):
                                print(f"Você ganhou!!!!!!!!! (:")
                                break
                        else:
                            revela_bombas(matriz)
                            gera_campo(matriz)
                            print(f"BOOOOOOOOOOOOOOOMMM")
                            break
                    elif resp == 2:
                        print(f"Insira a coordenada onde ficará a bandeira.")
                        linha = int(input())
                        coluna = int(input())
                        if matriz[linha-1][coluna-1]["aberto"] == False:
                            if matriz[linha-1][coluna-1]["bandeira"] == False:
                                bandeira_matriz(matriz, linha, coluna, True)
                            else:
                                print(f"Casa protegida por bandeira.")
                        else:
                            print(f"A casa já está aberta.")
                        gera_campo(matriz)
                else:
                    print(f"1. Abrir quadrado\n2. Colocar bandeira\n3. Tirar bandeira")
                    resp = int(input())
                    if resp == 1:
                        print(f"Insira a coordenada para abertura.")
                        linha = int(input())
                        coluna = int(input())
                        if not verifica_bombas(matriz, linha-1, coluna-1):
                            efeito_cascata_dfs(matriz, linha-1, coluna-1)
                            gera_campo(matriz)
                            if verifica_vitoria(matriz,num_minas):
                                print(f"Você ganhou!!!!!!!!! (:")
                                break
                        else:
                            revela_bombas(matriz)
                            gera_campo(matriz)
                            print(f"BOOOOOOOOOOOOOOOMMM")
                            break
                    elif resp == 2:
                        print(f"Insira a coordenada onde ficará a bandeira.")
                        linha = int(input())
                        coluna = int(input())
                        if matriz[linha-1][coluna-1]["aberto"] == False:
                            if matriz[linha-1][coluna-1]["bandeira"] == False:
                                bandeira_matriz(matriz, linha, coluna, True)
                            else:
                                print(f"Casa protegida por bandeira.")
                        else:
                            print(F"A casa já está aberta.")
                        gera_campo(matriz)
                    elif resp == 3:
                        print(f"Insira a coordenada da bandeira a ser retirada.")
                        linha = int(input())
                        coluna = int(input())
                        if matriz[linha-1][coluna-1]["bandeira"] == True:
                            bandeira_matriz(matriz, linha, coluna, False)
                        else:
                            print(f"Não nenhuma bandeira na casa.")
                        gera_campo(matriz)
            print(f"\nContinuar? (1 para continuar e 0 para sair)")
            respf = int(input())
            if respf == 0:
                break
if __name__=="__main__":
    main()
