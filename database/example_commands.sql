# GETS ACTORS ASSOCIATED WITH SHOW ID '2'
select actor.first_name, actor.last_name from actor inner join show_actor on actor.id = show_actor.id_actor where show_actor.id_show=2;

# GETS ACTORS ASSOCIATED WITH SHOW ID '2' AND THE TITLE OF THE SHOW
select `show`.title,actor.first_name, actor.last_name from actor inner join show_actor on actor.id = show_actor.id_actor inner join `show` on `show`.id = show_actor.id_show where show_actor.id_show=2;

# GETS GENRES ASSOCIATED WITH SHOW ID '2'
select genre.name from genre inner join show_genre on genre.id = show_genre.id_genre where show_genre.id_show=2;

# GETS RATING ASSOCIATED WITH SHOW ID '2'
select rating.name from rating inner join show_rating on rating.id = show_rating.id_rating where show_rating.id_show=2;
