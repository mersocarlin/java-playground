/*Computação Gráfica - Desenvolvido por:
                           Thiago Linhares de Oliveira
                           Marcelo de Souza*/

/* Includes */
#include <stdio.h>
#include <stdlib.h>
#include <gl/glut.h>
#include "normal.c"

/* Minhas Definições */
#define FALSE 0
#define TRUE 1
#define ESC 27

struct TFigura{
   float rX, rY, rZ, ang, pX, pY, pZ, corR, corG, corB, quantP, quantT;
   TipoPontos *pontos, *index;
};


struct TRobo{
    float rot, x, y, z;
    TFigura dorso;
};

struct TLabirinto{
    float rot, x, y, z;
    TFigura fig;
};

/* Protótipos das funções*/
int inicializaGL(GLvoid);                               // inicialização openGL
void desenhaMundo(GLvoid);                              // desenha os objetos
void mostraMundo(void);                                 // quando estiver ocioso mostre o mundo
void desenhaJanela(int largura, int altura);            // confirações para a janela
void teclasComuns(unsigned char tecla, int x, int y);   // manipula ações com teclas
void teclasEspeciais(int tecla, int x, int y);          // manipula ações com teclas
void carregaValores(void);
void posicionaMundo(void);

/* Variáveis Globais */
int JanelaPrincipal;
int Altura, Largura;
bool fullScreen = false;
float posX = 0, posZ = 0, rotUpDown = 0, posUpDown = 0;  //variaveis de controle do mundo
int rot = 0;
TRobo robo;
TLabirinto labirinto;
TipoPontos normal;
float piover180 = 3.14159265358979/180;



/*==========================================*/
/* Definicao de vetores contendo dados de   */
/* cor, intensidade e pos. de fontes de luz.*/
/*==========================================*/
GLfloat			diffuseLight[] = { 1.0f, 1.0f, 1.0f, 1.0};
GLfloat			ambientLight[] = { 0.2f, 0.2f, 0.4f, 1.0};
GLfloat			emissiveLight[] = { 0.0f, 0.0f, 0.0f, 1};
GLfloat			lightPos[] = { -10.0f, 100.0f, -30.0f, 1.0f };


/*---------------------------------------------------------------------*/
void desenhaLabirintoMech()
{
  int i;
  posicionaMundo();
  glTranslatef( labirinto.x, labirinto.y, labirinto.z);
  glRotatef( labirinto.rot, 0, 1, 0);
//  labirinto.x = labirinto.x + 0.1;
 // labirinto.rot =   labirinto.rot + 0.5;

  // fig
  glColor3f( labirinto.fig.corR, labirinto.fig.corG, labirinto.fig.corB );
//  glColor3f( 1, 1, 1);
//  glTranslatef( labirinto.fig.pX, labirinto.fig.pY, labirinto.fig.pZ);
//  glRotatef( labirinto.fig.ang, labirinto.fig.rX, labirinto.fig.rY, labirinto.fig.rZ);
  for( i = 0; i < labirinto.fig.quantT; i++)
  {
    int p1, p2, p3;
    p1 = labirinto.fig.index[i].x;
    p2 = labirinto.fig.index[i].y;
    p3 = labirinto.fig.index[i].z;
    glBegin(GL_TRIANGLES);


    calculaNormal( labirinto.fig.pontos[p1],
                   labirinto.fig.pontos[p2],
                   labirinto.fig.pontos[p3],
                         &normal);
      glNormal3f(normal.x, normal.y, normal.z);

      glVertex3i( labirinto.fig.pontos[p1].x, labirinto.fig.pontos[p1].y , labirinto.fig.pontos[p1].z );
      glVertex3i( labirinto.fig.pontos[p2].x, labirinto.fig.pontos[p2].y , labirinto.fig.pontos[p2].z );
      glVertex3i( labirinto.fig.pontos[p3].x, labirinto.fig.pontos[p3].y , labirinto.fig.pontos[p3].z );
    glEnd();
  }
};

/*---------------------------------------------------------------------*/
void desenhaRobo(void)
{
  int i;
  glTranslatef( robo.x, robo.y, robo.z);
  glRotatef( robo.rot, 0, 1, 0);
//  robo.x = robo.x + 0.1;
//  robo.rot =   robo.rot + 0.5;

  // Dorso
  glColor3f( robo.dorso.corR, robo.dorso.corG, robo.dorso.corB );
//  glTranslatef( robo.dorso.pX, robo.dorso.pY, robo.dorso.pZ);
//  glRotatef( robo.dorso.ang, robo.dorso.rX, robo.dorso.rY, robo.dorso.rZ);
  for( i = 0; i < robo.dorso.quantT; i++)
  {
    int p1, p2, p3;
    p1 = robo.dorso.index[i].x;
    p2 = robo.dorso.index[i].y;
    p3 = robo.dorso.index[i].z;
    glBegin(GL_TRIANGLES);

    if(i > 43){
        if(i < 55){
            glColor3f( 0, 0, 0 );
        }
    }

    if(i > 67){
        if(i < 70){
            glColor3f( 1, 1, 1 );
        }
    }

    if(i > 31){
        if(i < 43){
            glColor3f( 1, 1, 1 );
    }}


    if(i > 55){
        if(i < 67){
            glColor3f( 0, 0, 0 );
        }
    }

    calculaNormal( robo.dorso.pontos[p1],
                   robo.dorso.pontos[p2],
                   robo.dorso.pontos[p3],
                         &normal);
      glNormal3f(normal.x, normal.y, normal.z);

      glVertex3i( robo.dorso.pontos[p1].x, robo.dorso.pontos[p1].y , robo.dorso.pontos[p1].z );
      glVertex3i( robo.dorso.pontos[p2].x, robo.dorso.pontos[p2].y , robo.dorso.pontos[p2].z );
      glVertex3i( robo.dorso.pontos[p3].x, robo.dorso.pontos[p3].y , robo.dorso.pontos[p3].z );
    glEnd();

  }
};

/*---------------------------------------------------------------------*/
void carregaValores(void)
{
  FILE *dados;
  int i;

  robo.x = 0;
  robo.y = 0;
  robo.z = -60;
  robo.rot = 0;

  labirinto.x = 0;
  labirinto.y = 0;
  labirinto.z = -60;
  labirinto.rot = 0;


// Labirinto
  dados = fopen("labirinto.txt", "r");
  fscanf(dados, "%f", &labirinto.fig.pX);
  fscanf(dados, "%f", &labirinto.fig.pY);
  fscanf(dados, "%f", &labirinto.fig.pZ);
  fscanf(dados, "%f", &labirinto.fig.rX);
  fscanf(dados, "%f", &labirinto.fig.rY);
  fscanf(dados, "%f", &labirinto.fig.rZ);
  fscanf(dados, "%f", &labirinto.fig.ang);
  fscanf(dados, "%f", &labirinto.fig.corR);
  fscanf(dados, "%f", &labirinto.fig.corG);
  fscanf(dados, "%f", &labirinto.fig.corB);
  fscanf(dados, "%f", &labirinto.fig.quantP);
  labirinto.fig.pontos = (TipoPontos *) malloc(sizeof(TipoPontos) * ( labirinto.fig.quantP ));
  for ( i = 0; i < labirinto.fig.quantP; i++)
  {
    fscanf(dados, "%f", &labirinto.fig.pontos[i].x);
    fscanf(dados, "%f", &labirinto.fig.pontos[i].y);
    fscanf(dados, "%f", &labirinto.fig.pontos[i].z);
  }

  fscanf(dados, "%f", &labirinto.fig.quantT);
  labirinto.fig.index = (TipoPontos *) malloc(sizeof(TipoPontos) * ( labirinto.fig.quantT ));
  for ( i = 0; i < labirinto.fig.quantT; i++)
  {
    fscanf(dados, "%f", &labirinto.fig.index[i].x);
    fscanf(dados, "%f", &labirinto.fig.index[i].y);
    fscanf(dados, "%f", &labirinto.fig.index[i].z);
  }
  fflush( dados );
  fclose( dados );
// Fim Labirinto

// dorso
  dados = fopen("dorso.txt", "r");
  fscanf(dados, "%f", &robo.dorso.pX);
  fscanf(dados, "%f", &robo.dorso.pY);
  fscanf(dados, "%f", &robo.dorso.pZ);
  fscanf(dados, "%f", &robo.dorso.ang);
  fscanf(dados, "%f", &robo.dorso.rX);
  fscanf(dados, "%f", &robo.dorso.rY);
  fscanf(dados, "%f", &robo.dorso.rZ);
  fscanf(dados, "%f", &robo.dorso.corR);
  fscanf(dados, "%f", &robo.dorso.corG);
  fscanf(dados, "%f", &robo.dorso.corB);
  fscanf(dados, "%f", &robo.dorso.quantP);
  robo.dorso.pontos = (TipoPontos *) malloc(sizeof(TipoPontos) * ( robo.dorso.quantP ));
  for ( i = 0; i < robo.dorso.quantP; i++)
  {
    fscanf(dados, "%f", &robo.dorso.pontos[i].x);
    fscanf(dados, "%f", &robo.dorso.pontos[i].y);
    fscanf(dados, "%f", &robo.dorso.pontos[i].z) ;
  }

  fscanf(dados, "%f", &robo.dorso.quantT);
  robo.dorso.index = (TipoPontos *) malloc(sizeof(TipoPontos) * ( robo.dorso.quantT ));
  for ( i = 0; i < robo.dorso.quantT; i++)
  {
    fscanf(dados, "%f", &robo.dorso.index[i].x);
    fscanf(dados, "%f", &robo.dorso.index[i].y);
    fscanf(dados, "%f", &robo.dorso.index[i].z);
  }
  fflush( dados );
  fclose( dados );
// Fim dorso
};



/*-----------------------------------------------------------------------------*/
void posicionaMundo(void)
{
  glLoadIdentity();
  glRotatef( rotUpDown, 1, 0, 0);
  glRotatef( -rot, 0, 1, 0);
  glTranslatef( -posX, posUpDown, -posZ);
}

/*---------------------------------------------------------------------*/
/* Função de Inicialização do Sistema */
int inicializaGL(GLvoid)
{
  glutSetCursor(GLUT_CURSOR_NONE);                      // Esconde o Cursor do mouse
  glShadeModel(GL_SMOOTH);                              // Tipo de Sombreamento Suavizado
  glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);                // Cor de Limpeza de tela

  // não Entendi
  glClearDepth( 1.0f );                                 // Inicialização do Buffer de Profundidade
  glEnable(GL_DEPTH_TEST );                             // Habilita tipo de Teste para o Buffer
  glDepthFunc(GL_LEQUAL);                               // Define tipo de Teste de Profundidade
  // =================

  glHint( GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST );  // Tipo de Pespectiva
  glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);      // Desenhar os dois lados de um objeto

  //definicao de parametros de luz
      glEnable(GL_LIGHTING);
      glLightfv(GL_LIGHT0, GL_AMBIENT, ambientLight);
	  glLightfv(GL_LIGHT0, GL_SPECULAR, diffuseLight);
	  glEnable(GL_LIGHT0);
	  glEnable(GL_COLOR_MATERIAL);
      glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, diffuseLight );
      glMateriali(GL_FRONT_AND_BACK, GL_SHININESS, 100);


  return TRUE;
}


/*---------------------------------------------------------------------*/
/* Função de desenho dos objetos*/
void desenhaMundo(GLvoid)
{
  glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  // Limpa a tela e o Buffer
  glLoadIdentity();
  //======================= Código dos objetos deve ser inserido aqui =======================
  posicionaMundo();
  glLightfv(GL_LIGHT0, GL_POSITION, lightPos);
  desenhaLabirintoMech();
  posicionaMundo();
  desenhaRobo();


  //=========================================================================================
  glutSwapBuffers();    // Troca os buffers de desenho e de exibição
}

/*---------------------------------------------------------------------*/
/* Função que será ativada sempre que o sistema estiver ocioso*/
void mostraMundo(void)
{
  desenhaMundo();
}


/*---------------------------------------------------------------------*/
/* Função de redesenho da Janela*/
void desenhaJanela(int largura, int altura)
{
  Altura = altura;
  Largura = largura;

  if ( ! altura )
    altura = 1;
  glViewport(0, 0, largura, altura);                    // Determina as coordenadas da ViewPort
  glMatrixMode(GL_PROJECTION);                          // Seta proxima operacao para a pilha de projeções
  glLoadIdentity();                                     // Resete a Matriz de Projeção
  gluPerspective(60, (GLfloat)largura/(GLfloat)altura, 1, 600);
  glMatrixMode(GL_MODELVIEW);                           // Seta proxima operacao para a pilha de Modelos
  glLoadIdentity();                                     // Resete a Matriz de Modelos de Projeção
}

/*---------------------------------------------------------------------*/
/* Função de controle de teclas comuns*/
void teclasComuns(unsigned char tecla, int x, int y)
{
  switch ( tecla )
  {
    case ESC:
      glutDestroyWindow( JanelaPrincipal );
      exit(0);
    break;

    //movimento do robo
    case 'a':
       robo.rot += 3;
    break;
    case 'd':
       robo.rot -= 3;
    break;
    case 'w':
        robo.x += (float)sin(robo.rot*piover180)*2;                  // Move On The X-Plane Based On Player Direction
        robo.z += (float)cos(robo.rot*piover180)*2;                  // Move On The Z-Plane Based On Player Direction
    break;
    case 's':
        robo.x -= (float)sin(robo.rot*piover180)*2;                  // Move On The X-Plane Based On Player Direction
        robo.z -= (float)cos(robo.rot*piover180)*2;                  // Move On The Z-Plane Based On Player Direction
    break;
  }
}


/*---------------------------------------------------------------------*/
/* Função de controle de teclas Especiais*/
void teclasEspeciais(int tecla, int x, int y)
{
  switch( tecla )
  {
    case GLUT_KEY_F12:
        if( fullScreen )
        {
          glutPositionWindow(200, 200);
          glutReshapeWindow(500, 400);
        }
        else
          glutFullScreen();
        fullScreen = !fullScreen;
    break;
    case GLUT_KEY_RIGHT:
      rot -= 3.0f;
    break;
    case GLUT_KEY_LEFT:
      rot += 3.0f;
    break;
    case GLUT_KEY_UP:
        posX -= (float)sin(rot*piover180)*2;                  // Move On The X-Plane Based On Player Direction
        posZ -= (float)cos(rot*piover180)*2;                  // Move On The Z-Plane Based On Player Direction
    break;
    case GLUT_KEY_DOWN:
        posX += (float)sin(rot*piover180)*2;                  // Move On The X-Plane Based On Player Direction
        posZ += (float)cos(rot*piover180)*2;                  // Move On The Z-Plane Based On Player Direction
    break;
    case GLUT_KEY_PAGE_UP:
        rotUpDown -= 5;   //muda a rotacao do up down
    break;
    case GLUT_KEY_PAGE_DOWN:
        rotUpDown += 5;   //muda a rotacao do up down
    break;
    case GLUT_KEY_HOME:
        posUpDown -= 5;   //muda a posicao up down
    break;
    case GLUT_KEY_END:
        posUpDown += 5;   //muda a posicao up down
    break;
  }
}

/*---------------------------------------------------------------------*/
/* Função Principal do Sistema*/
int main(int argc, char* argv[])
{
  carregaValores();
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
  glutInitWindowPosition(200, 200);
  glutInitWindowSize(500, 400);
  JanelaPrincipal = glutCreateWindow("Computação Gráfica - OpenGL - Mundo 3D");
  glutReshapeFunc( desenhaJanela );
  glutDisplayFunc( desenhaMundo );
  glutKeyboardFunc( teclasComuns );
  glutSpecialFunc( teclasEspeciais );
  glutIdleFunc( mostraMundo );
  inicializaGL();
  glutMainLoop();
  return 0;
}


