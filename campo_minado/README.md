# Campo Minado em Python

> **Projeto acadêmico desenvolvido para a disciplina de Programação IV**  
> *Curso Técnico em Informática para Internet — IFES Campus Serra*

![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)

### MECÂNICA:
As regras são as mesmas do campo minado origial: O jogador pode abrir casas, colocar bandeiras em casas que deduziu que haveria uma mina e pode retirar bandeiras.
Todos as ações se baseiam na entrada da coordenada da casa que sofrerá a ação (número da linha e depois o número da coluna).

### APRENDIZADOS E DIFICULDADES SUPERADAS: 

* **Efeito Cascata com DFS:** Durante o projeto, tive que entender como a aplicar o mecanismo de abertura de casas em série do próprio campo minado. A solução encontrada foi implementar o algoritmo **DFS (*Depth-First Search* / Busca em Profundidade)**,
sobre na matriz do tabuleiro, utlizando as coordenadas como referência das adjacências. 
* **Gerenciamento de Estado com Dicionários:** Um dos maiores desafios foi definir como guardar o histórico e o estado do jogo na memória. A solução adotada foi estruturar o tabuleiro como uma matriz de dicionários, onde cada quadrado possui seus próprios atributos booleanos e numéricos:

```python
# Estrutura de estado de cada quadrado da matriz
{
    "mina": False,     # True se contiver uma bomba
    "aberto": False,   # True se já foi revelado pelo jogador
    "bandeira": False, # True se estiver marcado com bandeira
    "vizinhos": 2      # Quantidade de minas ao redor (0 a 8)
}
```
### CONSIDERAÇÕES FINAIS
Confesso que este pequeno trabalho me proporcionou a oportunidade de aprender novas ideias de uso para estruturas de dados (matrizes e dicionários). E também me permitiu
a conhecer mais dos algoritmos de busca em grafos implícitos, compreendendo na prática qual abordagem era a mais adequada para atender aos requisitos de um jogo real.
