#language: pt

@searchTest
Funcionalidade: Busca no sistema SOC

  @searchBlog
  Cenario: Validar busca no blog
    Dado que o usuario esteja no sistema da SOC
    Quando realizar busca da noticia "NR-26"
    Entao o sistema direciona o usuario para a tela da noticia pesquisada
    E exibe o titulo com a chave buscada

  @searchAccredited
  Cenario: Validar busca de credenciado
    Dado que o usuario esteja no sistema da SOCNET
    Quando realizar busca dos parceiro no cep "38400162"
    Entao o sistema exibe a lista de credenciados daquela regiao
    E ao clicar na opcao de saiba mais
    Entao o sistema direciona o usuario para a tela de detalhamento do credenciado
