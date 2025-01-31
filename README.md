# Chemistry Visualizer
Molecule visualizer based on the indigo library

## Installation:
- look `src/deploy.sh`

## Usage:

- Generate image for SMILE by GET:<br/>
    http://localhost:8080/draw?smiles=OC1=CC=CNC1
  <br/>URL-encoded `CC(C=C(O[Pd]OC(=CC(=O)C)C)C)=O`:<br/>
    http://localhost:8080/draw?smiles=CC%28C%3DC%28O%5BPd%5DOC%28%3DCC%28%3DO%29C%29C%29C%29%3DO
- Generate image for SMILE by POST:<br/>
    URL: http://localhost:8080/draw <br/>
    JSON body:
    ```JSON
    {
        "description": "CC(C=C(O[Pd]OC(=CC(=O)C)C)C)=O"
    }
    ```
- Generate image for Chemical table file by POST:<br/>
  URL: http://localhost:8080/draw <br/>
  JSON body:<br/>
    ```JSON
    {
    "description": "\nCDK     1222161748\n\n8  7  0  0  0  0  0  0  0  0999 V2000\n1.2990   -0.7500    0.0000 S   0  0  0  0  0  0  0  0  0  0  0  0\n0.0000    0.0000    0.0000 H   0  0  0  0  0  0  0  0  0  0  0  0\n2.5981   -0.0000    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n3.8971   -0.7500    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n3.8971   -2.2500    0.0000 H   0  0  0  0  0  0  0  0  0  0  0  0\n5.1962   -0.0000    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n6.4952    0.7500    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n2.5981    1.5000    0.0000 S   0  0  0  0  0  0  0  0  0  0  0  0\n1  2  1  0  0  0  0\n1  3  1  0  0  0  0\n3  4  1  0  0  0  0\n4  5  1  0  0  0  0\n4  6  1  0  0  0  0\n6  7  3  0  0  0  0\n3  8  2  0  0  0  0\nM  END\n"
    }
    ```
- For convenience, two URLs have been added: `/draw/by_text` and `/draw/by_file`. Examples with `curl`<br/>
  By text (SMILES):<br/>
    ```console
    curl -X POST -F description="CC(C=C(O[Ni]OC(=CC(=O)C)C)C)=O" http://localhost:8080/draw/by_text > result.svg
    ```
  By file (MOL):<br/>
    ```console
    curl -X POST -F "description=@examples/ctf.mol" http://localhost:8080/draw/by_file > result.svg
    ```
  
- Example of an answer with an error:
    - Wrong string
    ```JSON
    {
        "description": "CC(C=C(O[Pd]OC(=CC(=)C))C"
    }
    ```
    - Response
    ```JSON
    {
    "error": "Internal Server Error",
    "message": "scanner: readIntFix(3): invalid number representation: \"1.2\"",
    "status": 500,
    "timestamp": "2025-01-31T23:05:16.1346459"
    }
    ```
  (For all these examples look `examples` directory)
