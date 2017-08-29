# XY Inc API
   Este projeto é um experimento de medidor de força de senha e foi inspirado em www.passwordmeter.com.  
   O presente repositório contém uma API do tipo RESTful. A API possui apenas um recurso, chamado "password" que contém um texto e um valor de força de 0-100.    
   É possível enviar uma senha e obter o valor de força da senha enviada.  


### Como testar a API
- `View`: Uma view simples React pode ser encontrada no repositório `https://github.com/madnilo/pwd-meter`
- `Cliente HTTP`: qualquer cliente pode ser usado.
- `Banco de Dados`: Nenhum banco de dados foi empregado.
- `IDE`: Utilizado o Spring Tool Suite (baseado no eclipse), porém pode ser executado em outras IDEs como o intelliJ.
- `Segurança`: Spring Security está configurado com autenticação HTTP Basic, porém foi setado para deixar passar todas as requisições, e para permitir CORS.

### Rotas



| Método | Path | Descrição |
| ------ | ------ | ------ |
| GET | /passwords/measure/{senha-a-ser-medida} | Retorna o valor de força da senha passada como path param. |



#### Um objeto

```json
{
"tex":"aaaaa",
"strength":"0",
}
```

### Testes Automatizados
   Ainda não foram empregados testes, pois a api precisa ser calibrada corretamente.

##### Regras de Negócio
   As regras de negócio foram baseadas como descritas no projeto original.  
   Os valores ainda não estão calibrados como deveria, porém os resultados podem ainda ser melhorados.