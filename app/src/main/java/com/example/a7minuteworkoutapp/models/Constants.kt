package com.example.a7minuteworkoutapp.models

import androidx.compose.runtime.mutableStateOf
import com.example.a7minuteworkoutapp.R

object Constants {

    fun getExerciseList(): ArrayList<Exercise>{
        val ExerciseList = ArrayList<Exercise>()

        // 1
        val jumpingJacks = Exercise(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(jumpingJacks)
        // 2
        val pushup = Exercise(
            2,
            "Push-Ups",
            R.drawable.ic_push_up,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(pushup)
        // 3
        val plank = Exercise(
            3,
            "Plank",
            R.drawable.ic_plank,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(plank)
        // 4
        val wallsit = Exercise(
            4,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(wallsit)
        // 5
        val abdominalcruch = Exercise(
            5,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(abdominalcruch)
        // 6
        val highKnees = Exercise(
            6,
            "High Knees running in place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(highKnees)
        // 7
        val lunge = Exercise(
            7,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(lunge)
        // 8
        val squat = Exercise(
            8,
            "Squat",
            R.drawable.ic_squat,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(squat)
        // 9
        val step_up_on_chair = Exercise(
            9,
            "Step Up on Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(step_up_on_chair)
        // 10
        val side_plank = Exercise(
            10,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(side_plank)
        // 11
        val pushup_And_Rotation = Exercise(
            11,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(pushup_And_Rotation)
        // 12
        val triceps_dip_on_chair = Exercise(
            12,
            "Triceps Dips on Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            mutableStateOf(false)
        )
        ExerciseList.add(triceps_dip_on_chair)

        return ExerciseList
    }


    fun getAllExerciseList():ArrayList<Exercise>{
        val allExerciseList = ArrayList<Exercise>()

        val jumping_Jacks = Exercise(
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(jumping_Jacks)

        val high_Knees = Exercise(
            2,
            "High Knees",
            R.drawable.high_knees,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(high_Knees)

        val butt_Kicks = Exercise(
            3,
            "Butt Kicks",
            R.drawable.butt_kicks,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(butt_Kicks)

        val ankle_Circles = Exercise(
            4,
            "Ankle Circles",
            R.drawable.ankle_circles,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(ankle_Circles)

        val hip_Circles = Exercise(
            5,
            "Hip Circles",
            R.drawable.hip_circles,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(hip_Circles)

        val push_ups = Exercise(
            6,
            "Push Ups",
            R.drawable.push_ups,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(push_ups)

        val triceps_Dips = Exercise(
            7,
            "Triceps Dips",
            R.drawable.triceps_dips,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(triceps_Dips)

        val arm_Circles = Exercise(
            8,
            "Arm Circles",
            R.drawable.arm_circles,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(arm_Circles)

        val inchWorm = Exercise(
            9,
            "Inch Worm",
            R.drawable.inchworm,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(inchWorm)

        val crunches = Exercise(
            10,
            "Crunches",
            R.drawable.crunches,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(crunches)

        val plank = Exercise(
            11,
            "Plank",
            R.drawable.plank,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(plank)

        val side_Plank = Exercise(
            12,
            "Side Plank",
            R.drawable.side_plank,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(side_Plank)

        val bicycle_Crunches = Exercise(
            13,
            "Bicycle Crunches",
            R.drawable.bicycle_crunches,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(bicycle_Crunches)

        val russian_Twist = Exercise(
            14,
            "Russian Twist",
            R.drawable.russian_twist,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(russian_Twist)

        val mountain_Climb = Exercise(
            15,
            "Mountain Climb",
            R.drawable.mountain_climb,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(mountain_Climb)

        val squats = Exercise(
            16,
            "Squats",
            R.drawable.squats,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(squats)

        val lunges = Exercise(
            17,
            "Lunges",
            R.drawable.lunges,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(lunges)

        val wall_Sit = Exercise(
            18,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(wall_Sit)

        val glute_Bridges = Exercise(
            19,
            "Glute Bridges",
            R.drawable.glute_bridges,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(glute_Bridges)

        val step_Ups = Exercise(
            20,
            "Step Ups",
            R.drawable.step_ups,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(step_Ups)

        val calf_Raises = Exercise(
            21,
            "Calf Raises",
            R.drawable.calf_raises,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(calf_Raises)

        val burphees = Exercise(
            22,
            "Burphees",
            R.drawable.burpees,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(burphees)

        val squat_Jumps = Exercise(
            23,
            "Squat Jumps",
            R.drawable.squat_jumps,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(squat_Jumps)

        val plank_Jacks = Exercise(
            24,
            "Plank Jacks",
            R.drawable.plank_jacks,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(plank_Jacks)

        val skaters = Exercise(
            25,
            "Skaters",
            R.drawable.skaters,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(skaters)

        val side_Stretch = Exercise(
            26,
            "Side Stretch",
            R.drawable.side_stretch,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(side_Stretch)

        val shoulder_Stretch = Exercise(
            27,
            "Shoulder Stretch",
            R.drawable.shoulder_stretch,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(shoulder_Stretch)

        val butterfly_Stretch = Exercise(
            28,
            "Butterfly Stretch",
            R.drawable.butterfly_stretch,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(butterfly_Stretch)

        val quad_Stretch = Exercise(
            29,
            "Quad Stretch",
            R.drawable.quad_stretch,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(quad_Stretch)

        val chest_Stretch = Exercise(
            30,
            "Chest Stretch",
            R.drawable.chest_stretch,
            false,
            mutableStateOf(false)
        )
        allExerciseList.add(chest_Stretch)

        return allExerciseList
    }

    fun getSelectedExercises(): ArrayList<Exercise> {
        val selectedExerciseList = ArrayList<Exercise>()
        val allExerciseList = getAllExerciseList()

        for (exercise in allExerciseList) {
            if (exercise.isSelected.value) {
                selectedExerciseList.add(exercise)
            }
        }

        return selectedExerciseList
    }
}
