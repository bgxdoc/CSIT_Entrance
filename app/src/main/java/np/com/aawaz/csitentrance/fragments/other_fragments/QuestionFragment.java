package np.com.aawaz.csitentrance.fragments.other_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import mehdi.sakout.fancybuttons.FancyButton;
import np.com.aawaz.csitentrance.R;
import np.com.aawaz.csitentrance.custom_views.QuizTextView;
import np.com.aawaz.csitentrance.interfaces.QuizInterface;
import np.com.aawaz.csitentrance.objects.SPHandler;

public class QuestionFragment extends Fragment implements View.OnClickListener {

    QuizTextView question, option1, option2, option3, option4;
    TextView questionRo, option1Ro, option2Ro, option3Ro, option4Ro;
    RelativeLayout option1Listener, option2Listener, option3Listener, option4Listener;
    FancyButton option1Selected, option2Selected, option3Selected, option4Selected;

    CardView submit;

    int clickedAns = 0;
    private QuizInterface listener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int code, int position, String question, String a, String b,
                                               String c, String d, String ans) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        args.putString("question", question);
        args.putString("a", a);
        args.putString("b", b);
        args.putString("c", c);
        args.putString("d", d);
        args.putString("ans", ans);

        args.putInt("code", code);
        args.putInt("position", position);

        fragment.setArguments(args);
        return fragment;
    }

    public static QuestionFragment newInstance(int code, int position, String question, String a, String b,
                                               String c, String d, String ans, String subject) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        args.putString("question", question);
        args.putString("a", a);
        args.putString("b", b);
        args.putString("c", c);
        args.putString("d", d);
        args.putString("ans", ans);

        args.putInt("code", code);
        args.putInt("position", position);


        args.putString("subject", subject);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        question = (QuizTextView) view.findViewById(R.id.questionWeb);
        option1 = (QuizTextView) view.findViewById(R.id.optionWeb1);
        option2 = (QuizTextView) view.findViewById(R.id.optionWeb2);
        option3 = (QuizTextView) view.findViewById(R.id.optionWeb3);
        option4 = (QuizTextView) view.findViewById(R.id.optionWeb4);

        questionRo = (TextView) view.findViewById(R.id.questionRobo);
        option1Ro = (TextView) view.findViewById(R.id.optionRobo1);
        option2Ro = (TextView) view.findViewById(R.id.optionRobo2);
        option3Ro = (TextView) view.findViewById(R.id.optionRobo3);
        option4Ro = (TextView) view.findViewById(R.id.optionRobo4);

        option1Listener = (RelativeLayout) view.findViewById(R.id.option1);
        option2Listener = (RelativeLayout) view.findViewById(R.id.option2);
        option3Listener = (RelativeLayout) view.findViewById(R.id.option3);
        option4Listener = (RelativeLayout) view.findViewById(R.id.option4);


        option1Selected = (FancyButton) view.findViewById(R.id.optSelected1);
        option2Selected = (FancyButton) view.findViewById(R.id.optSelected2);
        option3Selected = (FancyButton) view.findViewById(R.id.optSelected3);
        option4Selected = (FancyButton) view.findViewById(R.id.optSelected4);

        submit = (CardView) view.findViewById(R.id.AnswerFab);

        YoYo.with(Techniques.SlideOutDown)
                .duration(0)
                .playOn(submit);
        submit.setOnClickListener(null);

        option1Listener.setOnClickListener(this);
        option2Listener.setOnClickListener(this);
        option3Listener.setOnClickListener(this);
        option4Listener.setOnClickListener(this);


        FancyButton tag = (FancyButton) view.findViewById(R.id.subjectTag);
        TextView yearTitleQuiz = (TextView) view.findViewById(R.id.yearTitleQuiz);
        yearTitleQuiz.setText(getResources().getStringArray(R.array.years)[getArguments().getInt("code")]);

        if (getArguments().getString("subject") == null) {
            tag.setText(SPHandler.getInstance().getSubjectName(SPHandler.getInstance().getSubjectCode(getArguments().getInt("code"), getArguments().getInt("position"))));
            tag.setTextColor(SPHandler.getInstance().getSubjectColor(SPHandler.getInstance().getSubjectCode(getArguments().getInt("code"), getArguments().getInt("position"))));
            tag.setBorderColor(SPHandler.getInstance().getSubjectColor(SPHandler.getInstance().getSubjectCode(getArguments().getInt("code"), getArguments().getInt("position"))));
        } else {
            tag.setText(SPHandler.getInstance().getSubjectName(getArguments().getString("subject")));
            tag.setTextColor(SPHandler.getInstance().getSubjectColor(getArguments().getString("subject")));
            tag.setBorderColor(SPHandler.getInstance().getSubjectColor(getArguments().getString("subject")));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String questionText = getArguments().getString("question");
        final String opt1Text = getArguments().getString("a");
        final String opt2Text = getArguments().getString("b");
        final String opt3Text = getArguments().getString("c");
        final String opt4Text = getArguments().getString("d");

        if (questionText.contains("<img")) {
            questionRo.setVisibility(View.GONE);
            question.setVisibility(View.VISIBLE);
            question.setScript(questionText);
        } else {
            question.setVisibility(View.GONE);
            questionRo.setVisibility(View.VISIBLE);
            questionRo.setText(Html.fromHtml(questionText));
        }


        if (opt1Text.contains("<img")) {
            option1Ro.setVisibility(View.GONE);
            option1.setVisibility(View.VISIBLE);
            option1.setScript(opt1Text);

        } else {
            option1.setVisibility(View.GONE);
            option1Ro.setVisibility(View.VISIBLE);
            option1Ro.setText(Html.fromHtml(opt1Text));
        }

        if (opt2Text.contains("<img")) {
            option2Ro.setVisibility(View.GONE);
            option2.setVisibility(View.VISIBLE);
            option2.setScript(opt2Text);

        } else {
            option2.setVisibility(View.GONE);
            option2Ro.setVisibility(View.VISIBLE);
            option2Ro.setText(Html.fromHtml(opt2Text));
        }

        if (opt3Text.contains("<img")) {
            option3Ro.setVisibility(View.GONE);
            option3.setVisibility(View.VISIBLE);
            option3.setScript(opt3Text);
        } else {
            option3.setVisibility(View.GONE);
            option3Ro.setVisibility(View.VISIBLE);
            option3Ro.setText(Html.fromHtml(opt3Text));
        }

        if (opt4Text.contains("<img")) {
            option4Ro.setVisibility(View.GONE);
            option4.setVisibility(View.VISIBLE);
            option4.setScript(opt4Text);
        } else {
            option4.setVisibility(View.GONE);
            option4Ro.setVisibility(View.VISIBLE);
            option4Ro.setText(Html.fromHtml(opt4Text));
        }
    }


    public void checkAnswer() {
        if ((clickedAns == 1 && getArguments().getString("ans").equals("a")) || (clickedAns == 2 && getArguments().getString("ans").equals("b")) ||
                (clickedAns == 3 && getArguments().getString("ans").equals("c")) || (clickedAns == 4 && getArguments().getString("ans").equals("d"))) {
            listener.selected(submit, true, getAnswer());
        } else {
            listener.selected(submit, false, getAnswer());
        }
    }

    public String getAnswer() {
        switch (getArguments().getString("ans")) {
            case "a":
                return getArguments().getString("a");
            case "b":
                return getArguments().getString("b");
            case "c":
                return getArguments().getString("c");
            case "d":
                return getArguments().getString("d");
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    public QuestionFragment setListener(QuizInterface listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onClick(View view) {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        if (clickedAns == 0)
            YoYo.with(Techniques.SlideInUp)
                    .duration(500)
                    .playOn(submit);

        option1Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorUnselected));
        option2Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorUnselected));
        option3Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorUnselected));
        option4Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorUnselected));
        switch (view.getId()) {
            case R.id.option1:
                clickedAns = 1;
                option1Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
                break;
            case R.id.option2:
                clickedAns = 2;
                option2Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
                break;
            case R.id.option3:
                clickedAns = 3;
                option3Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
                break;
            case R.id.option4:
                clickedAns = 4;
                option4Selected.setBorderColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
                break;
        }

    }
}