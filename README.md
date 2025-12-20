# API Documentação — Plantei 🌱

> API para gerenciamento de plantas, usuários, jardins e plantas pertencentes aos usuários

## Oque é o plantei?
O Plantei é um projeto criado para ajudar desde iniciantes até pessoas experientes no cultivo de plantas. A plataforma permite acompanhar e compartilhar informações sobre o seu jardim com outras pessoas que também sejam responsáveis pelos cuidados, facilitando a comunicação e a organização das tarefas.

A jardinagem é um hábito relaxante e saudável, mas pode se tornar um desafio quando estamos começando, quando temos pouco tempo disponível, quando há muitas plantas para cuidar ou quando dividimos as responsabilidades com outras pessoas. Nessas situações, é comum ficarmos sem saber se as plantas já foram regadas, adubadas ou podadas — e isso pode prejudicar o cultivo, já que nem todas as espécies toleram o excesso de água ou cuidados repetidos.

Diferentemente de outros aplicativos, que geralmente permitem registrar eventos apenas quando criamos lembretes pré-definidos, o Plantei possibilita adicionar qualquer evento que a planta tenha passado de forma espontânea, sem depender de lembretes. Isso torna o uso mais fluido, natural e alinhado ao dia a dia do usuário.

Pensando em resolver esses problemas de forma simples, colaborativa e eficiente, surgiu o Plantei.


## 📁 Entities (Domínios do Sistema)
| Entidade     | Descrição |
|--------------|-----------|
| **Plant** | Planta categorizada (espécie/base), usada como modelo. |
| **User** | Usuário do sistema. |
| **PlantUser** | Representa a planta específica de um usuário, com características próprias (apelido, data de aquisição, garden). |
| **Garden** | Jardim do usuário, agrupamento de plantas. |
| **GardenMenber** | Representa a relação entre o jardim e usuarios que partipam do jardim e não são adimin. |
| **EventPlant** | Representa um evento pelo qual a planta ddo usuario passou (exemplo: rega, adubação, replante). |

---

## Modelo Logico

<img width="1060" height="809" alt="Untitled (1)" src="https://github.com/user-attachments/assets/536afd8b-b394-41e2-ab13-115935ae7ead" />


---

## EndPoints desenvolvidos

### Plants
Listar todas as plantas

```GET /api/plant```

Obter planta pelo ID
 
```GET /api/plant/{id}```

Cadastrar nova planta

```POST /api/plant```

Atualizar planta

```PUT /api/plant/{id}```

Deletar planta

```DELETE /api/plant/{id}```

------------------------------------------------------------------------

### User
Listar todos os usuarios

```GET /api/user```

Obter usuario pelo ID
 
```GET /api/user/{id}```

Cadastrar novo usuario

```POST /api/user```

Atualizar usuario

```PUT /api/user/{id}```

Deletar usuario

```DELETE /api/user/{id}```

------------------------------------------------------------------------

### PlantUser

Listar todas as plantas de usuários (relações)

`GET /api/plant-user`

Obter relação pelo ID

`GET /api/plant-user/{id}`

Cadastrar nova relação planta-usuário

`POST /api/plant-user`

Atualizar relação planta-usuário

`PUT /api/plant-user/{id}`

Deletar relação planta-usuário

`DELETE /api/plant-user/{id}`

------------------------------------------------------------------------

### Garden

Listar todos os jardins

`GET /api/garden`

Obter jardim pelo ID

`GET /api/garden/{id}`

Cadastrar novo jardim

`POST /api/garden`

Atualizar jardim

`PUT /api/garden/{id}`

Deletar jardim

`DELETE /api/garden/{id}`

------------------------------------------------------------------------


## Como rodar o projeto

### Requesitos:
- Ter o docker

### Passo 1 - Clonar o projeto
No terminal
```git clone git@github.com:Gabriella-Aquino/Plantei---JavaSpring.git```

### Passo 2 - Buidar e rodar o Docker

```docker compose up --build```


