SELECT type_dino, color
FROM Coloring_dinosaur
         INNER JOIN Dinosaur d on d.dinosaur_id = Coloring_dinosaur.id_dinosaur
         INNER JOIN Coloring c on c.id = Coloring_dinosaur.id_color where d.type_dino='Велоцираптор';
